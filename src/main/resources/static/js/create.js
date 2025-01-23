var sendToServer = document.getElementById("send_to_server");

sendToServer.addEventListener("click", sendDataToServer);

async function sendDataToServer() {
    var bName = document.getElementById("name").value;
    var bDescription = document.getElementById("description").value;
    var bDate = document.getElementById("date").value;

    if (!bName || !bDate) {
        alert("Пожалуйста, заполните все поля корректно!");
        return;
    }

    try {
        const response = await fetch('/birthdays', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ 
                name: bName, 
                description: bDescription, 
                date: bDate 
            }),
        });

        if (!response.ok) {
            throw new Error("Некорректные данные. Длина имени: 64 символа, длина описания: 128 символов.");
        }

        const data = await response.json();

        alert("Запись успешно добавлена.");

        window.location.href = "/";
    }
    catch (error) {
        alert("Ошибка: " + error.message);
    }
}