function getXmlHttp() {
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
};

// Отправка запроса Post
function post(name, message) {

	var request = "Ошибка получения!";
    // Создаём объект XMLHTTP
    var xmlhttp = getXmlHttp();

    // Открываем асинхронное соединение
    xmlhttp.open("POST", name, true);

    // Отправляем кодировку
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

	// Отправляем POST-запрос
	xmlhttp.send(message+"\n");

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) { // Ответ пришёл
			if(xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)

				request = xmlhttp.responseText;
				console.log("Получили вот это:"+request);
				if(request=="error")
					console.log("при создании произошла ошибка");
				else {
					console.log("Создание прошло успешно");
				//	location.href = 'http://localhost/list_message.html?'+request;
				}

			}
		}
	};


}


var numberСlasses = 0;
let numbersSubjects = new Array();
numbersSubjects.push(0);


function addClass(){

    numbersSubjects.push(0);

    const class_list = document.getElementById("class_list");
    class_list.innerHTML += "<div id = \"class_block\" class=\"brd\"" + numberСlasses + "\">"+
    "<div style= \"line-height: 1.8;\">"+
        "<span> Класс: </span>"+
        "<input  id = \"input_class"+numberСlasses +"\"  type=\"text\" size=\"15\" required placeholder=\"Имя класса\">"+
    "</div>"+
    "<div style= \"line-height: 2;\">"+
        "<span style=\"margin-left: 100px;\">Предмет</span>"+
        "<span style=\"margin-left: 220px;\">Учитель</span>"+
        "<span style=\"margin-left: 150px;\">Количество часов</span>"+
    "</div>"+

    "<div id = \"subject_list"+numberСlasses +"\">"+
        "<div>"+
            "<input id = \"input_subject"+numberСlasses+'.'+numbersSubjects[numberСlasses] +"\"  type=\"text\" size=\"32\" requiredplaceholder=\"Название предмета\">"+
            "<input id = \"input_teacher"+numberСlasses+'.'+numbersSubjects[numberСlasses] +"\"  type=\"text\" size=\"35\" required placeholder=\"Имя учителя\">"+
            "<input id = \"input_hours"+numberСlasses+'.'+numbersSubjects[numberСlasses] +"\"  input type=\"number\" min=\"1\" value=\"1\" size=\"5\" required>"+
        "</div>"+
    "</div>"+
    "<button id = \"plus_subject"+numberСlasses +"\" style=\"margin-left: 25px;\"> Добавить предмет </button>"+
    "</div>";


    numbersSubjects[numberСlasses]++;

    for (var i = 0; i <= numberСlasses; i++){
        document.getElementById("plus_subject"+i).addEventListener('click', function(){
            addSubject(this.id.substring(12)); });
    }

   numberСlasses++;
}

addClass();addClass();


function addSubject(i){
    const subject_list = document.getElementById("subject_list" + i);

    subject_list.innerHTML += "<div>"+
    "<input id = \"input_subject" +i+'.'+ numbersSubjects[i] + "\"  type=\"text\" size=\"32\" required requiredplaceholder=\"Название предмета\">"+
    "<input id = \"input_teacher" +i+'.'+ numbersSubjects[i] + "\"  type=\"text\" size=\"35\" required required placeholder=\"Имя учителя\">"+
    "<input id = \"input_hours" +i+'.'+ numbersSubjects[i] + "\"  input type=\"number\" min=\"1\" value=\"1\" size=\"5\" required>"+
    "<div>";

    numbersSubjects[i]++;
}


const buttonPlusClass = document.getElementById('plus_class') ;

buttonPlusClass.addEventListener('click', ()=>{  addClass(); });



const buttonGenerateSchedule = document.getElementById('generate_schedule');

buttonGenerateSchedule.addEventListener('click', ()=>{           
    var answer = numberСlasses + '\n';


    for (var i = 0; i < numberСlasses; i++){
        answer += document.getElementById("input_class" + i).value+
        '\n' + numbersSubjects[i];
        for (var j = 0; j < numbersSubjects[i]; j++){
            answer += '\n' + document.getElementById("input_subject" + i +'.'+j).value;
            answer += '\n' + document.getElementById("input_teacher" + i +'.'+j).value;
            answer += '\n' + document.getElementById("input_hours" + i +'.'+j).value;
        }
        answer += '\n'
    }



    console.log("Отправили: "+ answer);         
    post("inputted", answer);

    var link = document.createElement('a');
	link.setAttribute('href', '/output.xlsx');
	link.setAttribute('download', 'output.xlsx');
    link.click();
    
});

