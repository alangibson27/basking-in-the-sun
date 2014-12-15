#!/bin/bash
echo -e "\033[0;32mDeploying updates to GitHub...\033[0m"

# Build the project. 
hugo -t hinatabokko 

# Add changes to git.
git add -A

# Commit changes.
msg="rebuilding site `date`"
git commit -m "$msg"

# Push source and build repos.
git push origin master
git subtree push --prefix=public git@github.com:alangibson27/basking-in-the-sun.git gh-pages
