## What
Adds `PUT /api/v1/stock-levels/bulk` so a warehouse import can update many products in one call.

## How
A new controller and service loop over the submitted items and update `product_stock`.

## Testing
Tried it manually with two products.
