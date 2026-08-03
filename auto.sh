#!/bin/bash

# 1. Abort any previous stuck merge state (if applicable)
git merge --abort 2>/dev/null

# 2. Start the merge, accepting upstream changes by default for conflicts
git merge upstream/master --allow-unrelated-histories --no-edit -X theirs

# 3. Restore YOUR version for every file listed in moddedfiles.txt
if [ -f moddedfiles.txt ]; then
    while IFS= read -r file || [ -n "$file" ]; do
        # Ignore empty lines and comments
        [[ -z "$file" || "$file" =~ ^# ]] && continue

        # Checkout local version of the protected file
        git checkout HEAD -- "$file" 2>/dev/null || echo "Warning: Could not restore $file"
    done < moddedfiles.txt
fi

# 4. Stage restored files and finalize the merge commit
git add .
git commit -m "Merged upstream updates while preserving protected custom files" --no-edit
