#!/usr/bin/env bash
# Publishes this repo to your GitHub account and creates the issues and pull requests used by the MCP lab.
set -euo pipefail

REPO_NAME="${1:-inventory-service-mcp-lab}"
BODIES="scripts/lab-setup/pr-bodies"

command -v gh >/dev/null || { echo "Install the GitHub CLI first: https://cli.github.com"; exit 1; }
gh auth status >/dev/null 2>&1 || { echo "Run 'gh auth login' first."; exit 1; }

cd "$(git rev-parse --show-toplevel)"
git rev-parse --verify main >/dev/null

echo "Creating private repository $REPO_NAME ..."
gh repo create "$REPO_NAME" --private --source . --remote origin

echo "Pushing branches and tag ..."
git push origin main
git push origin feature/low-stock-alerts feature/bulk-stock-update feature/product-id-validation docs/readme-endpoint-table
git push origin v0.1.0

echo "Opening pull requests ..."
gh pr create --base main --head feature/bulk-stock-update \
  --title "feat: add bulk stock update endpoint" --body-file "$BODIES/bulk-stock-update.md"
gh pr create --base main --head feature/product-id-validation \
  --title "feat: validate product id format" --body-file "$BODIES/product-id-validation.md"
gh pr create --base main --head docs/readme-endpoint-table \
  --title "docs: update README endpoint table" --body-file "$BODIES/readme-endpoint-table.md"

echo "Creating issues ..."
gh issue create --title "Add pagination to the low-stock endpoint" \
  --body "GET /api/v1/stock-levels/low returns every low-stock product. Add page and size parameters."
gh issue create --title "Alert webhook URL should be configurable" \
  --body "The alert webhook address needs to move into application.yml so each environment can set its own."
gh issue create --title "Document the SQS integration plan" \
  --body "Write a short design note on publishing stock-change events to SQS after commit."

echo "Done. Open: $(gh repo view --json url -q .url)"
