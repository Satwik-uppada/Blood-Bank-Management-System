# Blood Bank Management System - Database Schema Diagram

This diagram provides a comprehensive view of all database entities, their relationships, and key attributes across the microservices.

![1746365893370](image/database-schema/1746365893370.png)



## Key Entity Details

### User-related Entities

#### `users` (user-service)

- Primary entity for authentication and authorization
- Stores role information (DONOR, HOSPITAL_ADMIN, BLOODBANK_ADMIN, SYSTEM_ADMIN)
- Contains soft delete for GDPR compliance
- Password stored with BCrypt encryption

### Blood Bank Entities

#### `blood_banks` (bloodbank-service)

- Stores information about blood bank facilities
- Categories include GOVERNMENT, RED_CROSS, CHARITABLE, PRIVATE

#### `blood_inventory` (bloodbank-service)

- Tracks available blood units by blood group
- Links to originating donation
- Updated in real-time as donations are processed and requests fulfilled

### Donation Process

#### `donations` (donation-service)

- Tracks donor contributions
- Status workflow: PENDING → SCHEDULED → COMPLETED → APPROVED/REJECTED
- Links to inventory once approved

### Request and Billing Process

#### `blood_requests` (bloodrequest-service)

- Hospital requests for blood units
- Status workflow: PENDING → APPROVED → INVOICE_GENERATED → FULFILLED/REJECTED

#### `blood_rates` (billing-service)

- Government-approved rates for blood units
- Can vary by blood group and blood bank
- Historical tracking with effective_from date

#### `invoices` (billing-service)

- Generated when blood requests are approved
- Links request to applicable rate
- Tracks payment status

#### `payment_transactions` (billing-service)

- Records payment details
- Supports multiple payment modes (UPI, CARD, CASH)

### Supporting Services

#### `notifications` (notification-service)

- Centralized notification tracking
- Multiple notification types (DONATION, REQUEST, PAYMENT)
- Retry mechanism for failed notifications

#### `audit_logs` (audit-service)

- Comprehensive activity tracking
- Important for regulatory compliance
- Captures all CRUD operations plus approvals

#### `hospitals` (hospital-service, optional)

- Enables multi-hospital management
- Links to hospital admin user

## Database Design Principles

1. **Soft Deletes**: All entities include `is_deleted` flag for recoverable deletions
2. **Audit Columns**: Most tables include `created_at` and `updated_at` timestamps
3. **Referential Integrity**: Foreign keys maintain data consistency
4. **Schema Separation**: Each microservice has its own database schema
5. **Enum Types**: Used for fixed value sets like status, roles, and blood groups

## Data Security Considerations

- Personally Identifiable Information (PII) requires appropriate encryption
- Medical records follow relevant healthcare data protection standards
- Transaction data needs secure handling and limited access
- Audit logs should be immutable and tamper-evident
