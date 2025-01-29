async function outputBlockElements(block, data) {
    try {
        data.forEach(d => {
            const birthdayElement = document.createElement('div');
            
            birthdayElement.classList.add('birthdays');

            const imgSrc = d.image ? `/images/get/${d.id}` : "images/default_avatar.png";

            birthdayElement.innerHTML = `
                <div class="birthday_id">
                    <div class="id">
                        <p>${d.id}</p>
                    </div>
                </div>
                <div class="birthday_avatar">
                    <img src="${imgSrc}" alt="Не удалось загрузить фото">
                </div>
                <div class="birthday_data">
                    <div class="name">
                        <h3>${d.name}</h3>
                    </div>
                    <div class="description">
                        <p>${d.description}</p>
                    </div>
                    <div class="date">
                        <p>${dateToRussianFormat(d.date)}</p>
                    </div>
                </div>
            `;

            block.appendChild(birthdayElement);
        });
    } 
    catch (error) {
        console.error("Ошибка загрузки данных:", error);
    }
}

function dateToRussianFormat(date) {
    var numbers = date.split("-");

    var withoutZeroDay = numbers[2].replace(/^0+/, '');

    var months = {
        "01": "января",
        "02": "февраля",
        "03": "марта",
        "04": "апреля",
        "05": "мая",
        "06": "июня",
        "07": "июля",
        "08": "августа",
        "09": "сентября",
        "10": "октября",
        "11": "ноября",
        "12": "декабря",
    };

    var newDate = `Дата рождения: ${withoutZeroDay} ${months[numbers[1]]} ${numbers[0]} года`; 

    return newDate;
}

async function getFeedBirthdaysData(timeValue) {
    try {
        const response = await fetch(`/feed/${timeValue}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error("Не удалось получить данные с сервера.");
        }

        return await response.json();
    } 
    catch (error) {
        console.error("Ошибка:", error);
        return 0;
    }
}

async function today() {
    const todayBlock = document.getElementById("today");
    todayBlock.innerHTML = '';
    outputBlockElements(todayBlock, await getFeedBirthdaysData("today"));
}

async function soon() {
    const soonBlock = document.getElementById("soon");
    soonBlock.innerHTML = '';
    outputBlockElements(soonBlock, await getFeedBirthdaysData("soon"));
}

async function future() {
    const futureBlock = document.getElementById("future");
    futureBlock.innerHTML = '';
    outputBlockElements(futureBlock, await getFeedBirthdaysData("future"));
};

today();
soon();
future();