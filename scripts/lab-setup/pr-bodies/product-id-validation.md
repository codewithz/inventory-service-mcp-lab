## What
Rejects malformed product ids (for example `abc` or `SKU1001`) with a 400 instead of a 404.

## How
`@Pattern` on the `productId` path variable in `StockController`, a handler for `ConstraintViolationException`, and a unit test for the handler.

## Testing
`mvn test`.
