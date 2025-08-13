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
                        { "term": { "roles.bleId": "0802" } },
                        { "term": { "roles.businessProductId": "31500" } },
                        { "term": { "roles.account.accountNumber": "00000000000052019009020" } }
                      ]
                    }
                  },
                  {
                    "bool": {
                      "must": [
                        { "term": { "roles.bleId": "0810" } },
                        { "term": { "roles.businessProductId": "0000" } },
                        { "term": { "roles.account.accountNumber": "0000000000000005161614158" } }
                      ]
                    }
                  }
                ],
                "minimum_should_match": 1
              }
            },
            "inner_hits": {
              "name": "matched_roles",
              "_source": [
                "roles.bleId",
                "roles.account.productCode",
                "roles.businessProductId",
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
