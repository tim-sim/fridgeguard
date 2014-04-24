$(function () {
    var datePirckerOptions = {
        "dateFormat": "yy-mm-dd"
    };

    $("#productionDate").datepicker(datePirckerOptions);
    $("#expirationDate").datepicker(datePirckerOptions);

    var fridgeForm = $("#fridgeForm");
    var fridgeName = $("#fridgeName");
    var fridgeSubmit = $("#fridgeSubmitBtn");

    fridgeForm.submit(function (event) {
        if (!fridgeName.is(':visible')) {
            event.preventDefault()
            fridgeSubmit.value("Save")
        } else {
            fridgeSubmit.value("Add")
        }
        ;
        fridgeName.slideToggle();
    })

    $("#productFormClearBt").click(function (event) {
        // clear fridge form
    })
});