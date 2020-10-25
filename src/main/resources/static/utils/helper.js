/**
 * 
 */

Handlebars.registerHelper('ifeq', function (a, b, options) {
	if (a == b) { return options.fn(this); }
    return options.inverse(this);
});

Handlebars.registerHelper('ifneq', function (a, b, options) {
    if (a != b) { return options.fn(this); }
    return options.inverse(this);
});

Handlebars.registerHelper('firstChar', function (a) {
    return a.substring(0, 1);
});

Handlebars.registerHelper('bigger', function (a, b) {
	if(a > b) { return true; }
    return false;
});

Handlebars.registerHelper('biggerAndEq', function (a, b) {
	if(a >= b) { return true; }
    return false;
});

Handlebars.registerHelper('smaller', function (a, b) {
	if(a < b) { return true; }
    return false;
});

Handlebars.registerHelper('smallerAndEq', function (a, b) {
	if(a >= b) { return true; }
    return false;
});

Handlebars.registerHelper('eq', function (a, b) {
	if(a == b) { return true; }
    return false;
});

Handlebars.registerHelper('pagination', function(currentIndex, totalPages, pageSize, options) {
    var pages = [];
    var from = currentIndex > Math.floor(pageSize / 2) ? (currentIndex - Math.floor(pageSize / 2)) : 1; 
    var to = totalPages > pageSize ? ((from + pageSize > totalPages) ? totalPages : from + pageSize - 1) : totalPages;
    if(to == totalPages) {
    	from -= (pageSize - (to - from) > 0 ? pageSize - (to - from) - 1 : 0);
    }
    if(from < 0) {
    	from = 1;
    }
    for(var i = from; i <= to; i += 1) {
        pages.push({
        		page: i,
        		current: i == currentIndex
        	});
    }
    return options.fn({pages: pages});
});