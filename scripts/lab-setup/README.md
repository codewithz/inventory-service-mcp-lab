# Lab setup scripts

`seed-github.sh` publishes this repository to your own GitHub account and creates the issues and pull requests the MCP lab uses.

## Before you run it

- Install the GitHub CLI (`gh`) and run `gh auth login`.
- Run the script from Git Bash, macOS Terminal or Linux. On Windows without Git Bash, use WSL.

## Run it

```bash
bash scripts/lab-setup/seed-github.sh inventory-service-mcp-lab
```

The repository is created as **private**. Your fine-grained token in the lab must be scoped to this repository.

## What you get

- Default branch `main` with a realistic commit history and a merged feature branch.
- Three open pull requests and three open issues.
- Tag `v0.1.0`.
