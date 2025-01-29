async function sendUpdateRequest(id) {
    var Bname = document.getElementById("name").value;
    var Bdescription = document.getElementById("description").value;
    var Bdate = document.getElementById("date").value;
    var photo = document.getElementById("photo").files[0];

    var today = new Date().toISOString().split("T")[0];

    if (!Bname || !Bdate) {
        alert("Пожалуйста, заполните все поля корректно!");
        return;
    }

    if (Bdate > today) {
        alert("Дата рождения не может быть в будущем.");
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
            throw new Error("Некорректные данные. Длина имени: 64 символа, длина описания: 128 символов.");
        }

        const updatedBirthday = await response.json();

        const birthdayId = updatedBirthday.id;

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
        var currentPhotoImg = document.getElementById("current_photo_img");

        Bname.value = data.name;
        Bdescription.value = data.description;
        Bdate.value = data.date;

        if (data.image) {
            currentPhotoImg.src = `/images/get/${data.id}`;
            currentPhotoImg.style.display = 'block';
        } 
        else {
            currentPhotoImg.src = "images/default_avatar.png";
            currentPhotoImg.style.display = 'block';
        }
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