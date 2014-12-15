#!/bin/bash

function sync_files {
   rsync -av --exclude "public/***" --exclude ".git/***" --exclude "bin/***" --include "content/*" --include "layouts/*" --include "static/*" --include "themes/*" --include "*.toml" ~/Projects/HinataBokko/ ~/.hugolocal/
}

# Kill existing Hugo process.
if [ -e ~/.hugolocal/hugo_pid ]; then
  HUGO_PID=`cat ~/.hugolocal/hugo_pid`
  echo "Killing $HUGO_PID"
  kill $HUGO_PID
fi

# Remove any existing .hugolocal directory and recreate it.
rm -rf ~/.hugolocal
mkdir ~/.hugolocal

# Set up sync of data from the project to .hugolocal
cd ~/.hugolocal
sync_files
hugo server --watch=true --buildDrafts=true --theme=hinatabokko &
echo $! > ~/.hugolocal/hugo_pid

# Trigger a sync of project files to .hugolocal any time a file is modified.
while inotifywait -e modify -r ~/Projects/HinataBokko/* > /dev/null 2>&1; do
   sync_files
done

