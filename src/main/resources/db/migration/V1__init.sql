CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE work_items (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    current_state VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE state_transitions (
    id UUID PRIMARY KEY,
    work_item_id UUID NOT NULL,
    from_state VARCHAR(50),
    to_state VARCHAR(50) NOT NULL,
    changed_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_state_transitions_work_item
ON state_transitions(work_item_id);
