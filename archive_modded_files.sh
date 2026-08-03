#!/bin/bash

INPUT_FILE="moddedfiles.txt"
OUTPUT_ARCHIVE="modded_files_archive.tar.gz"
TEMP_FILE_LIST="files_to_compress.tmp"

if [ ! -f "$INPUT_FILE" ]; then
    echo "Error: Input file '$INPUT_FILE' not found!"
    exit 1
fi

echo "Processing $INPUT_FILE..."
> "$TEMP_FILE_LIST"

while IFS= read -r line || [ -n "$line" ]; do
    # Strip Windows \r line endings from input lines if present
    line=$(echo "$line" | tr -d '\r')

    # 1. Strip bracketed metadata (e.g., )
    clean_path=$(echo "$line" | sed 's/\[.*\]//g' | xargs)

    # 2. Skip empty lines
    [ -z "$clean_path" ] && continue

    # 3. Strip leading slash
    clean_path="${clean_path#/}"

    # 4. Strip project folder name if 'src/' or 'assets/' is in the path
    if [[ "$clean_path" =~ src/ ]]; then
        relative_path="src/${clean_path#*src/}"
    elif [[ "$clean_path" =~ assets/ ]]; then
        relative_path="assets/${clean_path#*assets/}"
    else
        relative_path="$clean_path"
    fi

    # 5. Check if the file or directory exists
    if [ -e "$relative_path" ]; then
        echo "$relative_path" >> "$TEMP_FILE_LIST"
        echo "Found: $relative_path"
    elif [ -e "$clean_path" ]; then
        echo "$clean_path" >> "$TEMP_FILE_LIST"
        echo "Found: $clean_path"
    else
        echo "Warning: Not found -> $relative_path"
    fi
done < "$INPUT_FILE"

if [ ! -s "$TEMP_FILE_LIST" ]; then
    echo "Error: No existing files found to archive."
    rm -f "$TEMP_FILE_LIST"
    exit 1
fi

echo -e "\nArchiving files into $OUTPUT_ARCHIVE..."
tar -czf "$OUTPUT_ARCHIVE" -T "$TEMP_FILE_LIST"

rm -f "$TEMP_FILE_LIST"
echo "Success! Archive created: $OUTPUT_ARCHIVE"
