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
result = list.reduce() //continue here
console.log('4.6 ', result); //result here

//7. Use reduce to sum the x-value of the objects in the array
list =  [{x: 1},{x: 2}, {x: 3}];
result = list.reduce() //continue here
console.log('4.7 ', result); //result here

//8

//9

//10