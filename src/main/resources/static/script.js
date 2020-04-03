let setup = {
    today: Date,
    collapsed: true
};

(function today() {

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

    return setup;
})();


function activateForm() {
    let buttonActivateForm = document.querySelector('#activateForm');
    let section = document.querySelector("#newAccount");

    if (setup.collapsed) {
        section.removeAttribute("hidden");
        setup.collapsed = !setup.collapsed;
        buttonActivateForm.innerHTML = "I changed my mind!";
    } else {
        section.setAttribute("hidden", "hidden");
        setup.collapsed = !setup.collapsed;
        buttonActivateForm.innerHTML = "Create Savings Account!";
    }
}
