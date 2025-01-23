async function sendUpdateRequest(id) {
    var Bname = document.getElementById("name").value;
    var Bdescription = document.getElementById("description").value;
    var Bdate = document.getElementById("date").value;

    if (!Bname || !Bdate) {
        alert("Пожалуйста, заполните все поля корректно!");
        return;
    }

    try {
        const response = await fetch(`/birthdays/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ 
                name: Bname, 
                description: Bdescription, 
                date: Bdate
            }),
        });

        if (!response.ok) {
            throw new Error(response.statusText);
        }

        await response.json();

        alert("Запись успешно изменена.");

        window.location.href = "/";
    }
    catch (error) {
        alert("Ошибка: " + error.message);
    }
}

async function getBirthdayDataById(id) {
    try {
        const response = await fetch(`/birthdays/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            window.location.href = `/`;
        }

        const data = await response.json();

        var Bname = document.getElementById("name");
        var Bdescription = document.getElementById("description");
        var Bdate = document.getElementById("date");

        Bname.value = data.name;
        Bdescription.value = data.description;
        Bdate.value = data.date;
    }
    catch (error) {
        console.error('Ошибка:', error);
    }
}

const urlParams = new URLSearchParams(window.location.search);

const id = urlParams.get('id');

if (id) {
    getBirthdayDataById(id);
}

var sendUpdatedToServer = document.getElementById("send_updated_to_server");

sendUpdatedToServer.addEventListener("click", () => sendUpdateRequest(id));