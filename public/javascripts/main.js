$(function () {
    var datePirckerOptions = {
        "dateFormat": "yy-mm-dd"
    };

    $("#productionDate").datepicker(datePirckerOptions);
    $("#expirationDate").datepicker(datePirckerOptions);

    var isFridgeForm = false;
    var fridgeForm = $("#fridgeForm");
    var fridgeName = $("#fridgeName");

    fridgeForm.submit(function (event) {
        if (!fridgeName.is(':visible')) {
            event.preventDefault()
        }
        ;
        fridgeName.slideToggle();
    })

    $("#fridgeFormResetBt").click(function (event) {
        // clear fridge form
    })
});