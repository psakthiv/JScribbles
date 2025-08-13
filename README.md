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
                "must": [
                  { "term": { "roles.bleid": "0802" } },
                  { "term": { "roles.account.productCode": "0000" } },
                  { "term": { "roles.account.accountNumber": "0000000000000005161614158" } }
                ]
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
    }
  },
  "aggs": {
    "grouped_by": {
      "terms": { "field": "partyId.keyword", "size": 10000 }
    }
  }
}
