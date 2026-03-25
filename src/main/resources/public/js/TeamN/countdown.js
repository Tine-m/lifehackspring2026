
// Removed magic numbers
const millisecondsInASecound = 1000;
const secoundsInAMinuite = 60;
const minutesInAHour  = 60;
const hoursInADay    =  24;

const Days = document.getElementById("dage");
const Hours = document.getElementById("timer");
const Minutes = document.getElementById("minutter");
const Secounds = document.getElementById("sekunder");

const examination = new Date("2026-06-11T00:00:00").getTime();

function timer (){
    const currentDate = new Date().getTime();
    const distance = examination - currentDate;

    //Got help from a video.
    //totalTime(milliseconds) / HoursInADay / minutesInAHour / secondsInAMinute / millisecondsInASecond
    const days    = Math.floor(distance / millisecondsInASecound / secoundsInAMinuite / minutesInAHour / hoursInADay);
    const hours   = Math.floor(distance / millisecondsInASecound / secoundsInAMinuite / minutesInAHour) % hoursInADay;
    const minutes = Math.floor(distance / millisecondsInASecound / secoundsInAMinuite) % minutesInAHour;
    const seconds = Math.floor(distance / millisecondsInASecound) % secoundsInAMinuite;

    //Sends values to the index.html
    Days.innerHTML = days;
    Hours.innerHTML = hours;
    Minutes.innerHTML = minutes;
    Secounds.innerHTML = seconds;
    if(distance < 0) {
        Days.innerHTML = "00";
        Hours.innerHTML = "00";
        Minutes.innerHTML = "00";
        Secounds.innerHTML = "00";
    }
}

//Updates the timer every second
setInterval(timer, millisecondsInASecound)