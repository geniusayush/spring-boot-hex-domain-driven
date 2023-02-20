- replace readme 
- replace springdemo with company name 
- change project name 

Within the set of entities of a system, some usually play a special role in their relationship to others. Consider an order consisting of line items. The order might expose a total which is calculated from the prices of the individual line items. It might only be in a valid state if it is more than a certain minimum total. The root entities of such a construct are usually conceptually elevated to a so called aggregate root and thus create certain implications:

The aggregate root is responsible to assert invariants on the entire aggregate. State changes on the aggregate always result in a valid result state or trigger an exception.

To fulfil this responsibility, only aggregate roots can be accessed by repositories (see Repositories). State changes involving non-root entities need to be applied by obtaining the aggregate root and triggering it on the root.

As an aggregate forms a natural consistency boundaries, references to other aggregates should be implemented in a by-id way.