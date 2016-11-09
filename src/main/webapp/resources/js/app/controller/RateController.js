/**
 * Created by Александр on 08.11.2016.
 */
$(function () {

    var rateCtrl = (function () {
        var rates;
        var methods = {
            init: function () {
                this.showRates();
            },
            showRates: function () {
                RateService.loadRates(function (data) {
                    rates = data;
                    render(rates);
                });
            }

        };

        function createTable(rates, id) {
            var table = $("<table>", {class: 'table', id: id});
            var row;
            var td;
            for(var i = 0; i < rates.length; i++) {
                row = $("<tr>");

                td = $("<td>");
                td.text(rates[i].Cur_Scale);
                row.append(td);

                td = $("<td>");
                td.text(rates[i].Cur_Abbreviation);
                row.append(td);

                td = $("<td>");
                td.text(rates[i].Cur_OfficialRate);
                row.append(td);

                table.append(row);

            }
            return table;
        }

        function render(rates) {
            var tableTitle = $('<div>');
            var myDate = new Date();
            if(rates.length > 0) {
                myDate = new Date(rates[0].Date);
            }
            var dateStr = myDate.getDate() + "." + (myDate.getMonth()+1) + "." + myDate.getFullYear();
            tableTitle.append("Курсы валют НБ РБ (" + dateStr + ")");
            var table = createTable(rates, "rate1");
            $('#rateContainer').append(tableTitle);
            $('#rateContainer').append(table);
        }


        return methods;
    }());

    rateCtrl.init();

});

