async function getAllBirthdayData() {
    try {
        const response = await fetch(`/birthdays`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error("Запрос данных к серверу не выполнен успешно.");
        }

        const data = await response.json();

        return data;
    }
    catch (error) {
        console.error('Ошибка:', error);
        return;
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

    var newDate = `День рождения: ${withoutZeroDay} ${months[numbers[1]]} ${numbers[0]} года`; 

    return newDate;
}

function today() {
    var todayBlock = document.getElementById("today");
}

function soon() {
    var soonBlock = document.getElementById("soon");
}

async function future(data) {
    const futureBlock = document.getElementById("future");
        
    futureBlock.innerHTML = '';

    try {
        const data = await getAllBirthdayData();

        data.forEach(d => {
            const birthdayElement = document.createElement('div');
            
            birthdayElement.classList.add('birthdays');
            
            birthdayElement.innerHTML = `
                <div class="birthday_id">
                    <div class="id">
                        <p>${d.id}</p>
                    </div>
                </div>
                <div class="birthday_avatar">
                    <img src="images/default_avatar.png">
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

            futureBlock.appendChild(birthdayElement);
        });
    } 
    catch (error) {
        console.error("Ошибка загрузки данных:", error);
    }
};

var data = getAllBirthdayData();
today();
soon();
future(data);