db.collection.aggregate([
  {
    $match: {
      $and: [
        { "document.metadata.core.dms.name": { $in: ["Captis"] } },
        { "document.metadata.core.documentClassification": { $in: ["Public", "Internal"] } }
      ]
    }
  },
  {
    $lookup: {
      from: "clientDoc",
      localField: "SearchResults._id",
      foreignField: "_id",
      as: "clientDoc"
    }
  },
  {
    $project: {
      _id: 1,
      metadata: 1,
      "SearchResults._id": 1,
      extendedMetadata: "$document.metadata.extended.xmetadataString",
      clientDoc: 1
    }
  },
  // Assuming this is a transformation that can be applied to each document.
  {
    $unwind: "$clientDoc"
  },
  {
    $addFields: {
      "clientDoc.projectedField": {
        // Add your transformation logic here.
      }
    }
  },
  // Reconstruct the SearchResults if necessary.
  {
    $group: {
      _id: "$_id",
      metadata: { $first: "$metadata" },
      SearchResults: { $push: "$SearchResults" },
      clientDoc: { $push: "$clientDoc" }
    }
  },
  {
    $project: {
      _id: 1,
      metadata: 1,
      SearchResults: {
        $slice: [ "$SearchResults", 0, 30 ] // Implementing the limit within the project stage
      },
      clientDoc: 1
    }
  },
  {
    $count: "totalCount"
  }
]);
