# Implementation Plan - Retail Store Application

This plan outlines the steps to create a professional Retail Store application with a Dashboard, Inventory Management, and Barcode-based Billing using Jetpack Compose, Room, and Google ML Kit.

## 1. Setup Dependencies
- [ ] Add Room, CameraX, and ML Kit Barcode Scanning dependencies to `libs.versions.toml`.
- [ ] Update `retailstore/build.gradle.kts` with new dependencies and Room compiler.
- [ ] Add necessary permissions (Camera) to `AndroidManifest.xml`.

## 2. Data Layer (Room Database)
- [ ] Create `Product` entity: `id`, `barcode`, `name`, `purchasePrice`, `salePrice`, `stock`.
- [ ] Create `Sale` entity: `id`, `timestamp`, `totalAmount`, `totalProfit`.
- [ ] Create `SaleItem` entity: `id`, `saleId`, `productId`, `quantity`, `priceAtSale`, `profitAtSale`.
- [ ] Define `RetailDao` with methods for product management and sales tracking.
- [ ] Implement `RetailDatabase`.
- [ ] Create a `RetailRepository` to handle data operations.

## 3. UI Layer (Jetpack Compose)

### Theme & Professional Look
- [ ] Update `Color.kt` and `Theme.kt` with an "Android Green" professional palette.
- [ ] Implement a `Navigation3` based host for screen transitions.

### Dashboard Screen
- [ ] Implement top card for "Today's Total Sale".
- [ ] Add toggleable "Today's Total Profit" with an eye icon.
- [ ] List recent sales in a professional card layout.

### Inventory Screen
- [ ] Searchable product list.
- [ ] Floating Action Button (FAB) to scan/add new products.
- [ ] Product detail/edit dialog.

### Barcode Scanning & Billing Screen
- [ ] Integrate CameraX preview.
- [ ] Use ML Kit Barcode Scanning to detect products.
- [ ] "Cart" functionality: accumulate scanned products, adjust quantities.
- [ ] Billing summary and "Complete Sale" action.

## 4. Verification
- [ ] Unit tests for Room Dao.
- [ ] Verify barcode scanning logic with mock data if a real device is unavailable.
- [ ] Build and run the app to verify the flow from Inventory -> Billing -> Dashboard.

## 5. Walkthrough
- [ ] Create a walkthrough documentation highlighting the features.
