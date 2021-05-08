# Assignment 4 - Graph

## Assignment description
See [the assignment](/Assignment4/A4-Neo4j-Cluster-Application.pdf)
- a cluster of minimum three Neo4j servers and two replicas
- a software development methodology graph database
- client application, which retrieves information from the database

## Status
We had some struggles with Maven, the `Graph Data Science` (GSD) library and others.

We implemented 7 queries through Java, altough none was an algorithm as specified in the assignment. 

## Solution
We set up the three cores and two replicas thanks to Martin's video guide.[1]  
We took the data from the assignments "Software Methodology" database and recreated it in our `core-1`. We the checked the other cores and replicas and found the same data, so we assume the cluster works as intended.  
Following this, we used Neo4j's guide on connecting to the database through Java.[2]  

### Database Setup
We set up 3 cores and 2 replicas as requested:
- `core-1`
- `core-2`
- `core-3`
- `replica-1`
- `replica-2`

And set up the GSD library by copying the plugin from another project and following a guide by Neo4j.[3]

#### Image of database setup
(Click for better resolution)  
![image](https://user-images.githubusercontent.com/37186286/117541260-51500d00-b013-11eb-803a-c566d978fa93.png)

#### Image of browser setup
(Click for better resolution)  
![image](https://user-images.githubusercontent.com/37186286/117541420-139fb400-b014-11eb-854a-8f460f239263.png)


### Database contents
We grabbed the data from the linked article, by going to its source where the statements were available behind a `//hiden` statement:   https://portal.graphgist.org/graph_gists/software-development-process-model-2/source  

#### Image of database contents:  
(Click for better resolution)  
![image](https://user-images.githubusercontent.com/37186286/117541064-6d06e380-b012-11eb-99fb-bed6f8b6e235.png)
#### Image of the graph:  
(Click for better resolution)  
![graph](https://user-images.githubusercontent.com/37186286/117540884-907d5e80-b011-11eb-8c6c-3ea782c4b9bb.png)


### Java solution

Source code can be found in [DBConnection.java](/Assignment4/src/main/java/DBConnection.java).  
.conf files can be found in [Resources](/Assignment4/src/main/resources).


#### Struggles we encountered
We had some struggles with the Maven dependencies regarding det Neo4J driver dependency. Besides that, the most of the challenges revolved around the part of the assignement that required the use of some of the algorithms in the GSD plugin. At this point we had already struggled a lot with the setup of the 3 cores and 2 replicas. We tried to implement a shotest path algorithm, but ended up abandoning that query. We couldn't use the `gds.beta.shortestPath.dijkstra` for some reason (we only had access to some of the other types) and at that point in time we had spent a lot of time on this assignment and decided that the time was better spent on trying to implement some queries, for future reference. All connections were made to `core-1` again due to the time already spent on this assignment.  
  
#### Result
We ended up making 7 queries of varying forms (see the DBConnection.java linked in at the start of this section).
* `getSingleNode` - takes the parameter `nodeName` and returns name and description of that node. We are using the `parameters()` functionality to assign the parameter in the query.
* `getMvcView` - returns the name of nodes in the MvcView
* `updateSingleNode` - takes to parameters, `nodeName` and `nodeDescription` and updates the specified node with the desired description. We are again using the `parameters()` functionality to assign the parameters in the query. The method returns the updated node with name and description.
* `getSoftwareDeveloper` - gets the relationships to "Software Developer" and prints their name and descriptions out.
* `getNodeDomainRelations` - takes the parameter `nodeDomainName` gets all relations to that `nodeDomain`, using the `parameters()` functionality to assign the parameters in the query.
* `getNodesWithoutRelations` - takes the String parameter `relationsFilter`. If the parameter is an empty String, the method gets all nodes without any relations. If the parameter is not empty the method gets all nodes without the specified relation. In this query we couldn't get the `parameters()` functionality to work. We suspect it's either due to some underlying formatting done by that call or the fact that we were trying to use the call to assign the parameter to the relation-part of the query. Either way, it didn't work and we decided to use the `String.format()` to format the query instead. 


<details><summary>Program output (<b>Click to reveal</b>)</summary>
<p>

```
1. Which, and how many, nodes are there in to "MvcView"?
Nodes in MvcView: ["_admin.html", "AdminNavigationView", "BillingAddressView", "CartView", "CreditCardDetailsView", "ContentEditView", "CheckoutView", "ContentListView", "_shopping.html", "CustomerNavigationView", "GiftCardDetailsView", "PaymentView", "ProductDetailView", "ProductEditView", "ProductListView", "ShippingAddressView"]
Size of MvcView: 16
**********************
2. What, and how many, relations are there to "Software Developer"?
Parent Node: "Software Development"
Parent relations: [name: "Web"	description: "Anything related to the Web-facing part of the system", name: "Testing"	description: "Anything related to testing the system", name: "Technical"	description: "Anything technical about the system implementation", name: "Process"	description: "Process-related items", name: "Knowledge"	description: "Anyone or anything that is a source of information or knowledge", name: "Human"	description: "Organization and people"]
Size of Parent relations: 6
**********************
3. Getting node by name "Term"
getSingleNode result: "Term": "Vocabulary terms and definitions"
**********************
4. Updating node by name "Term"
updateSingleNode result: "Term": "0.5917302176931089"
**********************
5. Getting node relations to nodeDomain "Technical"
Relations to Technical: [name: "MvcView"	description: "INCLUDED_IN", name: "CodeFolder"	description: "INCLUDED_IN", name: "Service"	description: "INCLUDED_IN", name: "DbTable"	description: "INCLUDED_IN", name: "Component"	description: "INCLUDED_IN", name: "CssFile"	description: "INCLUDED_IN", name: "CodeSolution"	description: "INCLUDED_IN", name: "AppLayer"	description: "INCLUDED_IN", name: "Location"	description: "INCLUDED_IN", name: "Server"	description: "INCLUDED_IN", name: "DbProcedure"	description: "INCLUDED_IN", name: "Platform"	description: "INCLUDED_IN", name: "JsFile"	description: "INCLUDED_IN", name: "Setting"	description: "INCLUDED_IN", name: "Permission"	description: "INCLUDED_IN", name: "LocalizationKey"	description: "INCLUDED_IN", name: "MvcController"	description: "INCLUDED_IN", name: "Database"	description: "INCLUDED_IN", name: "DbFunction"	description: "INCLUDED_IN", name: "DbSchema"	description: "INCLUDED_IN", name: "Environment"	description: "INCLUDED_IN", name: "File"	description: "INCLUDED_IN", name: "DbView"	description: "INCLUDED_IN", name: "CodeProject"	description: "INCLUDED_IN", name: "Software Development"	description: "PART_OF"]
Size of relations: 25
**********************
6. Find nodes with no relations
Nodes filtered by getNodesWithoutRelations: [name: "Admin", name: "Catalog.css", name: "Checkout.css", name: "Shopping.css", name: "Validation.css", name: "BonzDB", name: "dbo", name: "dbo.ufnGetDiscountedPrice", name: "dbo.ufnGetExtendedPrice", name: "CartRequirements.docx", name: "DisablePurchaseFlag.docx", name: "Checkout", name: "Content Management", name: "Product Management", name: "Help Desk, Tier 1", name: "Help Desk, Tier 2", name: "Network Administrators", name: "Production Support", name: "Collins, Sasha", name: "Porter, Rick E.", name: "Quick, Kelly", name: "Williams, Garnet", name: "Req CT-3-1", name: "Req CT-2-1", name: "Req CT-4-1", name: "Add Availability Flag", name: "ProductManagement_EditProductDetails", name: "Product Management Test Suite", name: "AvailableProductsView"]
Size: 29
**********************
7. Find nodes with no "PART_OF" relations
Nodes filtered by getNodesWithoutRelations: [name: "Audience", name: "CodeFolder", name: "Component", name: "CssFile", name: "MvcController", name: "Defect", name: "Document", name: "Feature", name: "File", name: "JsFile", name: "Location", name: "LocalizationKey", name: "AppLayer", name: "MvcView", name: "Permission", name: "Person", name: "Platform", name: "UserProfile", name: "Publication", name: "Release", name: "Requirement", name: "Role", name: "Setting", name: "Skill", name: "CodeSolution", name: "Service", name: "Task", name: "Term", name: "UserStory", name: "CodeProject", name: "Admin", name: "Warehouse", name: "Change set 1931", name: "Change set 1956", name: "Change set 2216", name: "Address.css", name: "Catalog.css", name: "Checkout.css", name: "Global.css", name: "Payment.css", name: "ProductDetails.css", name: "ProductList.css", name: "Shopping.css", name: "Validation.css", name: "CartController", name: "CheckoutController", name: "ContentController", name: "PaymentController", name: "ProductController", name: "PromotionController", name: "BonzDB", name: "dbo", name: "Defect 2819", name: "Defect 2816", name: "dbo.ufnGetDiscountedPrice", name: "dbo.ufnGetExtendedPrice", name: "CartRequirements.docx", name: "DisablePurchaseFlag.docx", name: "InventoryProductEntity", name: "ShoppingProductEntity", name: "Break Fix Environment", name: "Dev Environment", name: "Integration Test Environment", name: "Production Environment", name: "Staging Environment", name: "UAT Environment", name: "Product.sql", name: "Checkout", name: "Content Management", name: "Product Management", name: "Database Administrators", name: "Developers", name: "Help Desk, Tier 1", name: "Help Desk, Tier 2", name: "Network Administrators", name: "Project Management (PMO)", name: "Production Support", name: "Quality Assurance", name: "Cart.js", name: "CreditCardPayment.js", name: "Checkout.js", name: "GiftCardPayment.js", name: "Product.js", name: "Validation.js", name: "_admin.html", name: "AdminNavigationView", name: "BillingAddressView", name: "CartView", name: "CreditCardDetailsView", name: "ContentEditView", name: "CheckoutView", name: "ContentListView", name: "_shopping.html", name: "CustomerNavigationView", name: "GiftCardDetailsView", name: "PaymentView", name: "ProductDetailView", name: "ProductEditView", name: "ProductListView", name: "ShippingAddressView", name: "ShoppingProduct_List_Get", name: "ShoppingProduct_Details_Get", name: "Busy, Betty", name: "CIO, Sylvia", name: "Debaron, Chuck", name: "Dev, David", name: "Dev, Donna", name: "Dev, Delilah", name: "Collins, Sasha", name: "Porter, Rick E.", name: "Quick, Kelly", name: "Williams, Garnet", name: "Mendez, Andrew", name: "Quigby, Susan", name: "Tester, Tommy", name: "Tester, Theresa", name: "Tester, Mihir", name: "Release v1.1", name: "Release v1.2", name: "Release v1.3", name: "Req CT-3-1", name: "Req CT-2-1", name: "Req CT-4-1", name: "Business Analyst", name: "CIO", name: "DBA", name: "Developer", name: "Project Manager", name: "QA Manager", name: "QA Tester", name: "InventoryProduct_List_Get", name: "Product", name: "Add Availability Flag", name: "ProductManagement_EditProductDetails", name: "ShoppingCart_AddProductToCart", name: "ShoppingCart_RemoveProductFromCart", name: "ShoppingCart_ChangeProductQuantity", name: "ShoppingCart_ViewCart", name: "Product Management Test Suite", name: "ShoppingCart_TestSuite", name: "ShoppingProductDetailsVM", name: "ShoppingProductListVM", name: "AvailableProductsView"]
Size: 143
```

</p>
</details>

## External references
[1]: MRV's walkthrough on setting up 3 cores and 1 replica: [Panopto](https://cphbusiness.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=551727ed-3f24-4c02-ba90-ad1d00f5b2a7)  
[2]: Neo4j developer guide for Java: https://neo4j.com/developer/java/  
[3]: Neo4j setting up GSD: https://neo4j.com/docs/graph-data-science/current/installation/#_neo4j_desktop  
