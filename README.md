GET asr-associations-uat/_search
{
  "size": 0,
  "query": {
    "bool": {
      "must": [
        { "term": { "active": true } },
        {
          "nested": {
            "path": "roles",
            "query": {
              "bool": {
                "should": [
                  {
                    "bool": {
                      "must": [
                        { "term":  { "roles.bleid": "0802" }},                 // legal entity
                        { "term":  { "roles.account.productCode": 31500 }}     // product
                        // { "term":  { "roles.account.accountNumber": "0000000000000005161614158" }} // <— include here iff acct# is part of the combo
                      ]
                    }
                  }
                  /* add more combos as needed:
                  ,{
                    "bool": {
                      "must": [
                        { "term": { "roles.bleid": "0803" }},
                        { "term": { "roles.account.productCode": 25 }}
                      ]
                    }
                  }
                  */
                ],
                "minimum_should_match": 1
              }
            },
            "inner_hits": {
              "name": "matched_roles",
              "_source": [
                "roles.bleid",
                "roles.account.productCode",
                "roles.account.accountNumber"
              ]
            }
          }
        }
      ]
      /* If accountNumber is just an optional prefilter across all roles (not part of the combo),
         move it OUTSIDE the nested and keep it as a separate filter like this:
      , "filter": [
          { "nested": {
              "path": "roles",
              "query": { "term": { "roles.account.accountNumber": "0000000000000005161614158" } }
          }}
        ]
      */
    }
  },
  "aggs": {
    "grouped_by": {
      "terms": { "field": "partyId.keyword", "size": 10000 }
    }
  }
}
