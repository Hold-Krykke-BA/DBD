# Assignment 2 - Column-based databases

### Table of contents

- [Assignment description](#assignment-description)
- [Discussion](#discussion)
- [Solution](#solution)
  * [Task 1 - Investigation](#task-1---investigation)
  * [Task 2 - Bloom Filters](#task-2---bloom-filters)
  * [Task 3 - Huffman coding](#task-3---huffman-coding)
  * [Task 4 - Map and Reduce](#task-4---map-and-reduce)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

## Assignment description
- [Local](resources/1fd325b2-assignment_column_db.pdf)  
- [Online](https://datsoftlyngby.github.io/soft2021spring/DBD/week-10/#6-column-based-database-hbase)

## Discussion
```diff
- add, if any. If deleted, update table of contents
```

## Solution

### Task 1 - Investigation

```diff
- answer here in README
```

#### 1. What is point of NoSQL databases?  
*[Source](https://stackoverflow.com/questions/4154635/what-is-nosql-what-is-purpose-of-nosql-where-can-i-get-sufficient-material-on)*  
The point of the borader category of database engines that fit under the NoSQL umbrella is generally speaking that they have some advantages over traditional relational SQL databases because they give up certain features to gain other performance, scalability and developer usability features.  
  
  
**What NoSQL generally give up:**  
* Relationships between entities (and by extension the joining of tables or models together in a query).
* Limited [ACID](https://en.wikipedia.org/wiki/ACID) transactions. The level of read consistency and atomic write/commit capabilities across one or more tables/entities varies by NoSQL engine.
* No standard domain language. 
* Less structured and rigid data model. NoSQL typically forces/gives more responsibility at the application layer and to the developer to define the schema.  
  
  
**What NoSQL generally gains:**  
* Easier to shard and distribute the data across a cluster and by extension higher read/write scalability.
* NoSQL have a looser data model so you can have sparser data sets and variable data sets organized in documents or name/value column sets. Data models are not as hard wired.
* Schema migrations can be easier but puts burden on application layer to adjust to changes in data model.


#### 2. What is the CAP theorem?
*[Source](https://en.wikipedia.org/wiki/CAP_theorem)*  
The CAP theorem, states that it is impossible for a distributed data store to simultaneously provide more than two out of the following three guarantees:  
  
* Consistency: Every read receives the most recent write or an error
* Availability: Every request receives a (non-error) response, without the guarantee that it contains the most recent write
* Partition tolerance: The system continues to operate despite an arbitrary number of messages being dropped (or delayed) by the network between nodes
  
![image](https://user-images.githubusercontent.com/35559774/113292862-570c5180-92f5-11eb-8e2a-f80f4bef9be1.png)  

> *No distributed system is safe from network failures, thus network partitioning generally has to be tolerated. In the presence of a partition, one is then left with two options: consistency or availability.  
> When choosing consistency over availability, the system will return an error or a time out if particular information cannot be guaranteed to be up to date due to network partitioning. When choosing availability over consistency, the system will always process the query and try to return the most recent available version of the information, even if it cannot guarantee it is up to date due to network partitioning.  
> In the absence of network failure – that is, when the distributed system is running normally – both availability and consistency can be satisfied.  
> CAP is frequently misunderstood as if one has to choose to abandon one of the three guarantees at all times. In fact, the choice is really between consistency and availability only when a network partition or failure happens; at all other times, no trade-off has to be made.  
Database systems designed with traditional ACID guarantees in mind such as RDBMS choose consistency over availability, whereas systems designed around the BASE philosophy, common in the NoSQL movement for example, choose availability over consistency.*

#### 3. What are ideal use cases of HBase?
*[Source](https://blog.cloudera.com/apache-hbase-dos-and-donts/#usecases)*  
Hbase is an ideal choice if there is a need for random, realtime read/write access to the data.
HBase should be considered when: 
* Loading data by key
* Searching data by key or range
* Serving data by key
* Querying data by key
* Storing data by row that doesn’t conform well to a schema.

### Task 2 - Bloom Filters
Bloom filters are used in hbase as an incredible optimization. 

```diff
- answer here in README
```

#### 1. What is a bloom filter?
*[Source](https://en.wikipedia.org/wiki/Bloom_filter)*  
A Bloom filter is a space-efficient probabilistic data structure, that is used to test whether an element is a member of a set. False positive matches are possible, but false negatives are not – in other words, a query returns either "possibly in set" or "definitely not in set".

#### 2. What is an advantage of bloom filters over hash tables?


#### 3. What is a disadvantage of bloom filters?


#### 4. Using your language of choice, implement a bloom filter with add and check functions. The backing bit-array can simply be a long (64 bit integer).


#### 5. If you are to store one million ASCII strings with an average size of 10 characters in a hash set, what would be the approximate space consumption?


#### 6. The following equation gives the required number of bits of space per inserted key, where epsilon is the false positive rate.  

<center><img src="resources/equation.gif"></center>

#### 7. How many bits per element are required for a 1% false positive rate?


#### 8. How many bits per element are required for a 5% false positive rate?


#### 9. If you are to store one million ASCII strings with an average size of 10 characters in a bloom filter, what would be the approximate space consumption, given an allowed false positive rate of 5%?.


### Task 3 - Huffman coding
HBase internally uses a compression that is a combination of LZ77 and Huffman Coding.
```diff
- answer here in README + links to a task3 folder
```
#### 1. Generate Huffmann Code (and draw the Huffmann Tree) based on the following string: “beebs beepps!!!!! their eerie ears hear pears”


#### 2. How many bits is the compressed string? How many bits is the raw ASCII string?


#### 3. Compress “pete is here” with the Huffmann tree from before.


#### 4. Write your own 10 word sentence. Generate the Huffmann Code (a new Huffmann Tree), and write a new compressed message (ie. in binary). Swap with one of your fellow students, and decompress each other’s message.
```diff
! Maybe insert a table with results :)
```


### Task 4 - Map and Reduce
Map and reduce is relevant because the HBase database uses MapReduce internally.
Out of the box it comes with a default setup, but it can be modified to your liking.

Example usages: http://hbase.apache.org/book.html#mapreduce.example

Results can be found in [task4\MapReduce.js](task4/MapReduce.js) and program output can be found below:

<details><summary>Program output (Click to reveal)</summary>
<p>

```js
$ node MapReduce.js 
Task 4.1  [ 1, 3, 4, 10 ]
Task 4.2  [
  '<h1>Intro</h1>',
  '<h1>Requirements</h1>',
  '<h1>Analysis</h1>',
  '<h1>Implementation</h1>',
  '<h1>Conclusion</h1>',
  '<h1>Discussion</h1>',
  '<h1>References</h1>'
]
Task 4.3  [ "I'M", 'YELLING', 'TODAY' ]
Task 4.4  [ 1, 4, 10, 5 ]
Task 4.5  [
  '<img src="https://imgs.xkcd.com/comics/red_spiders_2.jpg">',
  '<img src="https://imgs.xkcd.com/comics/love.jpg">',
  '<img src="https://imgs.xkcd.com/comics/schrodinger.jpg">',
  '<img src="https://imgs.xkcd.com/comics/secrets.jpg">',
  '<img src="https://imgs.xkcd.com/comics/counter-red-spiders.jpg">',
  '<img src="https://imgs.xkcd.com/comics/found.jpg">',
  '<img src="https://imgs.xkcd.com/comics/want.jpg">',
  '<img src="https://imgs.xkcd.com/comics/penny_arcade.jpg">'
]
Task 4.6  15
Task 4.7  6
Task 4.8  [ 1, 2, 3, 4, 5, 6 ]
Task 4.9  [ 2, 4, 5 ]
Task 4.10  {
  '46': [ { name: 'Rikke', age: 46 }, { name: 'Mathias', age: 46 } ],
  '47': [ { name: 'Michael', age: 47 } ]
}
```

</p>
</details>
