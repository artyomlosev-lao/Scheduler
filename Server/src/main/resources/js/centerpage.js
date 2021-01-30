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

function post(message, name) {

	var request = "Ошибка получения!";
    // Создаём объект XMLHTTP
    var xmlhttp = getXmlHttp();

    // Открываем асинхронное соединение
    xmlhttp.open('POST', name, true);

    // Отправляем кодировку
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Отправляем POST-запрос
    //xmlhttp.send("a=" +  + "&b=" + encodeURIComponent(b));
    xmlhttp.send(message+"\n");

    // Ждём ответа от сервера
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) { // Ответ пришёл
            if(xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)

				console.log(request);
				request = xmlhttp.responseText;
				if(request=="LoginTrue"){
					location.href = 'http://localhost/admin.html';
				}
			  
            }
        }
    };
	
}

function download(){
	var link = document.createElement('a');
	link.setAttribute('href', '/temp.xlsx');
	link.setAttribute('download', 'temp.xlsx');
	link.click();
}

const button = document.getElementById("downloadBut");
button.addEventListener("click", ()=>{
	post("inputted", "inputted");
	download();
});

function PopUpHide(){
	$("#popup").hide();
}

function PopUpShow(){
	$("#popup").show();
}

PopUpHide();

document.getElementById("admin").addEventListener("click", ()=>{PopUpShow();});
document.getElementById("exit").addEventListener("click", ()=>{PopUpHide();});
document.getElementById("login").addEventListener("click", ()=>{
	const login = document.getElementById("inputLogin");
	const password = document.getElementById("inputPassword");
	post(login.value+"\n"+password.value, "login");
});









