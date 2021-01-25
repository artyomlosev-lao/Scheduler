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

