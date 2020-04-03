let setup = {
    today: Date,
    collapsed: true
};

(function init() {

    setup.today = moment(new Date()).format('DD/MM/YYYY');
    let formattedDate = setup.today.toString();

    let localDateDisplay = document.querySelector("#localDateTime");
    localDateDisplay ? localDateDisplay.innerHTML = formattedDate : "none";

    let dateObj = document.querySelector('#starting');
    if (dateObj) {
        dateObj.setAttribute("min", formattedDate);
        dateObj.setAttribute("placeholder", formattedDate);
        dateObj.setAttribute("value", formattedDate);
    }

    document.querySelector("#newAccount").style.visibility = "hidden";

    return setup;
})();


function activateForm() {
    let buttonActivateForm = document.querySelector('#activateForm');
    let section = document.querySelector("#newAccount");

    if (setup.collapsed) {
        section.style.visibility = "visible";
        setup.collapsed = !setup.collapsed;
        buttonActivateForm.innerHTML = "I changed my mind!";
    } else {
        section.style.visibility = "hidden";
        setup.collapsed = !setup.collapsed;
        buttonActivateForm.innerHTML = "Create Savings Account!";
    }
}
