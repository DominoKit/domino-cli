#!/usr/bin/env bash

set -e

SYSTEM_CONTEXT="$(cat AI_CONTEXT.md)"

exec codex "$SYSTEM_CONTEXT

---
You must follow the above rules for all subsequent requests.
" "$@"
