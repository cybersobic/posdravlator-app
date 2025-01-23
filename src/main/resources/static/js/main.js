var editButton = document.getElementById("edit_button");
var deleteButton = document.getElementById("delete_button");

editButton.addEventListener("click", editDate);
deleteButton.addEventListener("click", deleteDate);

async function editDate() {
    var data = await checkDataByNumber();

    if(data === 0) {
        return;
    }

    window.location.href = `/edit?id=${data.id}`;
}

async function deleteDate() {
    var data = await checkDataByNumber();

    if(data === 0) {
        return;
    }

    try {
        const response = await fetch(`/birthdays/${data.id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        if (response.ok) {
            alert(`Запись с ID ${data.id} успешно удалена.`);
            location.reload();
        } 
        else {
            throw new Error(`${response.statusText}`);
        }
    } 
    catch (error) {
        console.error('Ошибка:', error);
    } 
}

async function checkDataByNumber() {
    var number = prompt("Введите номер записи:");

    if(number === null) {
        return 0;
    }

    if (!number || isNaN(number.trim())) {
        alert("Ошибка! Некорректный ввод.");
        return 0;
    }

    try {
        const response = await fetch(`/birthdays/${number.trim()}`);

        if (!response.ok) {
            throw new Error('Запись с таким номером не найдена.');
        }

        const data = await response.json();

        return data;
    } 
    catch (error) {
        alert(`Ошибка: ${error.message}`);
        return 0;
    }
}