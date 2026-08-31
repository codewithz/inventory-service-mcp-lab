# Team Conventions

## Java service classes
1. Every class has a class-level Javadoc comment.
2. Use constructor injection. Do not put `@Autowired` on fields.
3. No hardcoded string literals for URLs, paths or configuration values. Use constants or `application.yml`.
4. Log at the start of every public method.

## Commits
Use a type prefix: `feat:`, `fix:`, `docs:`, `test:`, `chore:`. Lowercase, imperative mood.

## Pull requests
- One logical change per PR.
- New endpoints include tests.
- Update the README endpoint table when an endpoint is added or changed.
