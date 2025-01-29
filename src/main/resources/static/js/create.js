var sendToServer = document.getElementById("send_to_server");

sendToServer.addEventListener("click", sendDataToServer);

async function sendDataToServer() {
    var bName = document.getElementById("name").value;
    var bDescription = document.getElementById("description").value;
    var bDate = document.getElementById("date").value;
    var photo = document.getElementById("photo").files[0];

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

        const birthday = await response.json();
        const birthdayId = birthday.id;

        if (photo) {
            const formData = new FormData();
            formData.append("photo", photo);

            const photoResponse = await fetch(`/images/upload/${birthdayId}`, {
                method: 'POST',
                body: formData,
            });

            if (!photoResponse.ok) {
                throw new Error("Ошибка при загрузке изображения.");
            }
        }

        alert("Запись успешно добавлена.");

        window.location.href = "/";
    }
    catch (error) {
        alert("Ошибка: " + error.message);
    }
}