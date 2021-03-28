/**
 * Task 4 - Map and Reduce
 */
var list;
var result;

//1. Map the list of numbers to a list of their square roots
list = [1, 9, 16, 100];
result = list.map(item => Math.sqrt(item));
console.log('4.1 ', result); //[ 1, 3, 4, 10 ]


//2. Map the list of words so each is wrapped in a <h1> tag
list = ["Intro", "Requirements", "Analysis", "Implementation", "Conclusion", "Discussion", "References"];
result = list.map(item => "<h1>" + item + "</h1>");
console.log('4.2 ', result);
/*
[
  '<h1>Intro</h1>',
  '<h1>Requirements</h1>',
  '<h1>Analysis</h1>',
  '<h1>Implementation</h1>',
  '<h1>Conclusion</h1>',
  '<h1>Discussion</h1>',
  '<h1>References</h1>'
]
*/


//3. Use map to uppercase the words (all letters)
list = ["i'm", "yelling", "today"];
result = list.map(item => item.toUpperCase());
console.log('4.3 ', result); //[ "I'M", 'YELLING', 'TODAY' ]


//4. Use map to transform words into their lengths
list = ["I", "have", "looooooong", "words"];
result = list.map(item => item.length);
console.log('4.4 ', result); //[ 1, 4, 10, 5 ]

//5. Use map to get all the image urls, and wrap them in img-tags.
var comics = require('../resources/comics.json');
//result = comics.map(item => "<img>" + item.img + "</img>") //this is wrapping
result = comics.map(item => "<img src=\"" + item.img + "\">")
console.log('4.5 ', result);
/*
[
  '<img src="https://imgs.xkcd.com/comics/red_spiders_2.jpg">',
  '<img src="https://imgs.xkcd.com/comics/love.jpg">',
  '<img src="https://imgs.xkcd.com/comics/schrodinger.jpg">',
  '<img src="https://imgs.xkcd.com/comics/secrets.jpg">',
  '<img src="https://imgs.xkcd.com/comics/counter-red-spiders.jpg">',
  '<img src="https://imgs.xkcd.com/comics/found.jpg">',
  '<img src="https://imgs.xkcd.com/comics/want.jpg">',
  '<img src="https://imgs.xkcd.com/comics/penny_arcade.jpg">'
]
*/

//6. Use reduce to sum the array of numbers
list = [1, 2, 3, 4, 5];
result = list.reduce((accumulator, currentValue) => accumulator += currentValue);
console.log('4.6 ', result); //15


//7. Use reduce to sum the x-value of the objects in the array
list = [{ x: 1 }, { x: 2 }, { x: 3 }];
result = list.reduce((total, current) => total += current.x, 0); //start total as int 0
console.log('4.7 ', result); //6


//8. Use reduce to flatten an array of arrays
list = [[1, 2], [3, 4], [5, 6]];
result = list.reduce((total, current) => total.concat(current), []) //start total as new array
console.log('4.8 ', result); //[ 1, 2, 3, 4, 5, 6 ]


//9. Use reduce to return an array of the positive numbers
list = [-3, -1, 2, 4, 5];
result = list.reduce((total, current) => current > 0 ? total.concat(current) : total, []) //add to array if positive, else return array as is (for next function loop)
console.log('4.9 ', result); //[ 2, 4, 5 ]


//10. The accumulator function can obviously use objects outside of itself.  Use reduce to implement groupBy. 
let people = [
  { name: 'Rikke', age: 46 },
  { name: 'Michael', age: 47 },
  { name: 'Mathias', age: 46 }
];

//The expected outcome is an array of people grouped by their age
let groupBy = (objectArray, property) => {
  return objectArray.reduce(function (acc, obj) {
    let key = obj[property] //people[index].age
    if (!acc[key]) { //newPeople[age] 
      acc[key] = [] //(if not exists, add)
    }
    acc[key].push(obj) //push current into grouped array
    return acc //return for reduce function
  }, {}) //start acc as empty obj
};

//Assignment expects groupedPeople to be 46: Rikke, Mathias & 47: Michael

let groupedPeople = groupBy(people, 'age'); //group people by key 'age'.
console.log('4.10 ', groupedPeople);

/*
{
  '46': [ { name: 'Rikke', age: 46 }, { name: 'Mathias', age: 46 } ],
  '47': [ { name: 'Michael', age: 47 } ]
}
*/