var removeContent = document.getElementById("removeContent");

var title = document.getElementById("title");
var person_name = document.getElementById("person_name");
var sequences = document.getElementsByClassName("sequence");
var len = sequences.length;

console.log(title);
console.log(person_name);

removeContent.addEventListener("click", function () {
	title.value = "";
	person_name.value = "";
	for(var i = 0; i < len; i++) {
		if(sequences[i].checked == true) {
			sequences[i].checked = false;
		}
	}
}); 