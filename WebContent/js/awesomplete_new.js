/**
 * Simple, lightweight, usable local autocomplete library for modern browsers
 * Because there weren’t enough autocomplete scripts in the world? Because I’m completely insane and have NIH syndrome? Probably both. :P
 * @author Lea Verou http://leaverou.github.io/awesomplete
 * MIT license
 */

(function () {
	
	var _ = function (input, field1, field2, field3, field4, field5, field6,field7, debitor_nummer, o) {
		var me = this;
	    // Keep track of number of instances for unique IDs
	
	    LookupKhachHang.count = (LookupKhachHang.count || 0) + 1;
	    this.count = LookupKhachHang.count;
	    LookupDebitor.count = (LookupDebitor.count || 0) + 1;
	    this.count = LookupDebitor.count;
		// Setup
	    
		this.isOpened = false;
		
		if(input != null){
			this.input = $(input);
		}else{
			return;
		}
		
		if (field1 != null) {
			this.field1 = $(field1);
		}
		if(field2 != null){
			this.field2 = $(field2);
		}
		if(field3 != null){
			this.field3 = $(field3);
		}
		
		if (field4 != null) {
			this.field4 = $(field4);
		}
		if(field5 != null){
			this.field5 = $(field5);
		}
		if(field6 != null){
			this.field6 = $(field6);
		}
		if(field7 != null){
			this.field7 = $(field7);
		}
		this.debitor_nummer = $(debitor_nummer);
		this.input.setAttribute("autocomplete", "off");
		this.input.setAttribute("aria-owns", "awesomplete_list_" + this.count);
		this.input.setAttribute("role", "combobox");

		o = o || {};

		configure(this, {
			minChars: 1,
			maxItems: 20,
			autoFirst: true,
			data: _.DATA,
			filter: _.FILTER_CONTAINS,
			sort: o.sort === false ? false : _.SORT_BYLENGTH,
			item: _.ITEM,
			replace: _.REPLACE
		}, o);

		this.index = -1;

		// Create necessary elements

		this.container = $.create("div", {
			className: "awesomplete",
			around: input
		});

		this.ul = $.create("ul", {
			className : "awesomeplete-contain",
			hidden: "hidden",
	        role: "listbox",
	        id: "awesomplete_list_" + this.count,
			inside: this.container
		});

		this.status = $.create("span", {
			className: "visually-hidden",
			role: "status",
			"aria-live": "assertive",
	        "aria-atomic": true,
	        inside: this.container,
	        textContent: this.minChars != 0 ? ("Type " + this.minChars + " or more characters for results.") : "Begin typing for results."
		});

		// Bind events

		this._events = {
			input: {
				"input": this.evaluate.bind(this),
				"blur": this.close.bind(this, { reason: "blur" }),
				"keydown": function(evt) {
					var c = evt.keyCode;

					// If the dropdown `ul` is in view, then act on keydown for the following keys:
					// Enter / Esc / Up / Down
					if(me.opened) {
						if (c === 13 && me.selected) { // Enter
							evt.preventDefault();
							me.select();
						}
						else if (c === 27) { // Esc
							me.close({ reason: "esc" });
						}
						else if (c === 38 || c === 40) { // Down/Up arrow
							evt.preventDefault();
							me[c === 38? "previous" : "next"]();
						}
					}
				}
			},
			form: {
				"submit": this.close.bind(this, { reason: "submit" })
			},
			ul: {
				"mousedown": function(evt) {
					var li = evt.target;

					if (li !== this) {

						while (li && !/li/i.test(li.nodeName)) {
							li = li.parentNode;
						}

						if (li && evt.button === 0) {  // Only select on left click
							evt.preventDefault();
							me.select(li, evt.target);
						}
					}
				}
			}
		};

		$.bind(this.input, this._events.input);
		$.bind(this.input.form, this._events.form);
		$.bind(this.ul, this._events.ul);

		if (this.input.hasAttribute("list")) {
			this.list = "#" + this.input.getAttribute("list");
			this.input.removeAttribute("list");
		}
		else {
			this.list = this.input.getAttribute("data-list") || o.list || [];
		}

		_.all.push(this);
	};

_.prototype = {
	set list(list) {
		
		if (Array.isArray(list)) {
			this._list = list;
		}
		else if (typeof list === "string" && list.indexOf(",") > -1) {
				this._list = list.split(/\s*,\s*/);
		}
		else { // Element or CSS selector
			
			list = $(list);

			if (list && list.children) {
				var items = [];
				slice.apply(list.children).forEach(function (el) {
					if (!el.disabled) {
						var text = el.textContent.trim();
						var value = el.value || text;
						var label = el.label || text;
						if (value !== "") {
							items.push({ label: label, value: value });
						}
					}
				});
				this._list = items;
			}
		}

		if (document.activeElement === this.input) {
			this.evaluate();
		}
	},

	get selected() {
		return this.index > -1;
	},

	get opened() {
		return this.isOpened;
	},

	close: function (o) {
		if (!this.opened) {
			return;
		}

		this.ul.setAttribute("hidden", "");
		this.isOpened = false;
		this.index = -1;

		$.fire(this.input, "awesomplete-close", o || {});
	},

	open: function () {
		this.ul.removeAttribute("hidden");
		this.isOpened = true;

		if (this.autoFirst && this.index === -1) {
			this.goto(0);
		}

		$.fire(this.input, "awesomplete-open");
	},

	destroy: function() {
		//remove events from the input and its form
		$.unbind(this.input, this._events.input);
		$.unbind(this.input.form, this._events.form);

		//move the input out of the awesomplete container and remove the container and its children
		var parentNode = this.container.parentNode;

		parentNode.insertBefore(this.input, this.container);
		parentNode.removeChild(this.container);

		//remove autocomplete and aria-autocomplete attributes
		this.input.removeAttribute("autocomplete");
		this.input.removeAttribute("aria-autocomplete");

		//remove this awesomeplete instance from the global array of instances
		var indexOfAwesomplete = _.all.indexOf(this);

		if (indexOfAwesomplete !== -1) {
			_.all.splice(indexOfAwesomplete, 1);
		}
	},

	next: function () {
		var count = this.ul.children.length;
		this.goto(this.index < count - 1 ? this.index + 1 : (count ? 0 : -1) );
	},

	previous: function () {
		var count = this.ul.children.length;
		var pos = this.index - 1;

		this.goto(this.selected && pos !== -1 ? pos : count - 1);
	},

	// Should not be used, highlights specific item without any checks!
	goto: function (i) {
		var lis = this.ul.children;

		if (this.selected) {
			lis[this.index].setAttribute("aria-selected", "false");
		}

		this.index = i;

		if (i > -1 && lis.length > 0) {
			lis[i].setAttribute("aria-selected", "true");
            
			this.status.textContent = lis[i].textContent + ", list item " + (i + 1) + " of " + lis.length;
            
            this.input.setAttribute("aria-activedescendant", this.ul.id + "_item_" + this.index);

			// scroll to highlighted element in case parent's height is fixed
			this.ul.scrollTop = lis[i].offsetTop - this.ul.clientHeight + lis[i].clientHeight;
		

			$.fire(this.input, "awesomplete-highlight", {
				text: this.suggestions[this.index]
			});
		}
	},

	select: function (selected, origin) {
		if (selected) {
			this.index = $.siblingIndex(selected);
		} else {
			selected = this.ul.children[this.index];
		}

		if (selected) {
			var suggestion = this.suggestions[this.index];

			var allowed = $.fire(this.input, "awesomplete-select", {
				text: suggestion,
				origin: origin || selected
			});

			if (allowed) {
				this.replace(suggestion);
				this.close({ reason: "select" });
				$.fire(this.input, "awesomplete-selectcomplete", {
					text: suggestion
				});
			}
		}
	},

	evaluate: function() {
		var me = this;
		var value = this.input.value;
		var count = 0;
		
		
		if (value.length >= this.minChars && this._list.length > 0) {
			this.index = -1;
			// Populate list with options that match
			this.ul.innerHTML = "";

			this.suggestions = this._list
				.map(function(item) {
				
					return new Suggestion(me.data(item, value));
				})
				.filter(function(item) {
					return me.filter(item, value);
				});

			if (this.sort !== false) {
				this.suggestions = this.suggestions.sort(this.sort);
			}

			this.suggestions = this.suggestions.slice(0, this.maxItems);

			this.suggestions.forEach(function(text, index) {
				me.ul.appendChild(me.item(text, value, index));
			});

			if (this.ul.children.length === 0) {
                
                this.status.textContent = "No results found";
                
				this.close({ reason: "nomatches" });
        
			} else {
				this.open();
        
                this.status.textContent = this.ul.children.length + " results found";
			}
		}
		else {
			this.close({ reason: "nomatches" });
            
                this.status.textContent = "No results found";
		}
	}
};

// Static methods/properties

_.all = [];

_.FILTER_CONTAINS = function (text, input) {
	
	input = change_alias(input);
	text   = change_alias(text);
	//return true;
	return RegExp($.regExpEscape(input.trim()), "i").test(text);
};

_.FILTER_STARTSWITH = function (text, input) {
	return RegExp("^" + $.regExpEscape(input.trim()), "i").test(text);
};

_.SORT_BYLENGTH = function (a, b) {
	if (a.length !== b.length) {
		return a.length - b.length;
	}

	return a < b? -1 : 1;
};

_.ITEM = function (text, input, item_id) {
	var rowData;
	
	var html = input.trim() === "" ? text : text.replace(RegExp($.regExpEscape(input.trim()), "gi"), "<mark>$&</mark>");

	if(this.field1 != null && this.field2 != null && this.field3 != null && this.field4!= null && this.field5 != null && this.field6 != null){
		
		
		rowData = 
			"<html>" +
				"<table style=' width: 600px;margin:0px' class='table table-bordered'>" +
					"<tr>" +
 	        		
							"<td class = 'awesome-three-column width100'>"+html.split('|')[1]+"</td>" +	   //invoice_name						
							"<td class = 'awesome-three-column width150'>"+html.split('|')[2]+"</td>" +	   //customer_name					
							"<td class = 'awesome-three-column width150'>"+html.split('|')[4]+"</td>" +    //customer_name_level1				
							"<td class = 'awesome-three-column width150'>"+html.split('|')[6]+"</td>" +    //user_name
					"</tr>"+
				"</table>" +
			"</html>";

	}else if(this.debitor_nummer==3){
		
		rowData = 
			"<html>" +
				"<table style=' width: 400px;margin:0px' class='table table-bordered'>" +
					"<tr>" +
 	        		
					"<td class = 'awesome-three-column width100'>"+html.split('|')[0]+"</td>" +	   //customer_id_level1_hidden						
					"<td class = 'awesome-three-column width150'>"+html.split('|')[1]+"</td>" +	   //customer_code_level1_hidden					
					"<td class = 'awesome-three-column width150'>"+html.split('|')[2]+"</td>" +    //customer_name_level1		
					"</tr>"+
				"</table>" +
			"</html>";
	}else if(this.debitor_nummer==4){
		
		rowData = 
			"<html>" +
				"<table style=' width: 200px;margin:0px' class='table table-bordered'>" +
					"<tr>" +
 	        		
					"<td class = 'awesome-three-column width100'>"+html.split('|')[1]+"</td>" +	   //tennvtt						
						
					"</tr>"+
				"</table>" +
			"</html>";
		
    }else if(this.debitor_nummer==5 || this.debitor_nummer==6){
		
		rowData = 
			"<html>" +
				"<table style=' width: 400px;margin:0px' class='table table-bordered ;  position: relative;' >" +
					"<tr>" +
 	        		
					"<td class = 'awesome-three-column width100'>"+html.split('|')[0]+"</td>" +	   //masp	
					"<td class = 'awesome-three-column width100'>"+html.split('|')[1]+"</td>" +	   //tensp	
					"<td class = 'awesome-three-column width100' style =' text-align: right;'>"+html.split('|')[2]+"</td>" +	   //dongia
					"<td class = 'awesome-three-column width100' style =' text-align: right;'>"+html.split('|')[3]+"</td>" +	   //sochaitren1thung
						
					"</tr>"+
				"</table>" +
			"</html>";
			
	}
	//SETTING is_show_form_lookup = false để tránh việc nhảy field khi nhấn phím qua lại lên xuống. 
	is_show_form_lookup = false;
	return $.create("li", {
		innerHTML: rowData,
		"aria-selected": "false",
        "id": "awesomplete_list_" + this.count + "_item_" + item_id
	});
};

_.REPLACE = function (text) {

	if(this.debitor_nummer==3){
		this.input.value = text.value.split('|')[2];//customer_name_level1
		this.field1.value = text.value.split('|')[0];//customer_id_level1_hidden	
		this.field2.value = text.value.split('|')[1];//customer_code_level1_hidden
	}else if (this.debitor_nummer==4) {
		this.input.value  = text.value.split('|')[1];//tennvtt
		this.field1.value = text.value.split('|')[0];//manvtt
	} else if(this.debitor_nummer==5) {
		this.input.value  = text.value.split('|')[0];//masp
		this.field1.value = text.value.split('|')[1];//tensp
		this.field2.value = parseFloat(text.value.split('|')[2]).toLocaleString();//dongia
		this.field3.value = text.value.split('|')[3];//sochaitren1thung
	} else if(this.debitor_nummer==6) {
		this.input.value  = text.value.split('|')[1];//tensp
		this.field1.value = text.value.split('|')[0];//masp
		this.field2.value = parseFloat(text.value.split('|')[2]).toLocaleString();//dongia
		this.field3.value = text.value.split('|')[3];//sochaitren1thung
	}else if(this.field1 != null && this.field2 != null && this.field3 != null && this.field4 != null && this.field5 != null && this.field6 != null){
		
//		data = data +responseText[i][0]+"|";//customer_id
// 		data = data +responseText[i][1]+"|";//invoice_name			 	        	
// 		data = data +responseText[i][2]+"|";//customer_name
// 		data = data +responseText[i][3]+"|";//customer_id_level1
// 		data = data +responseText[i][4]+"|";//customer_name_level1
// 		data = data +responseText[i][5]+"|";//user_id
// 		data = data +responseText[i][6]+"";//user_name	

		if(this.debitor_nummer ==1){
			this.input.value = text.value.split('|')[1];//maKH
			this.field1.value = text.value.split('|')[2];//tenKH
		}else{
			this.input.value = text.value.split('|')[2];//maKH
			this.field1.value = text.value.split('|')[1];//tenKH
		}
		
		
		this.field2.value = text.value.split('|')[4];//customer_name_level1
		this.field3.value = text.value.split('|')[6];//nvtt_name	
		this.field4.value = text.value.split('|')[3];//customer_id_level1_hidden
		this.field5.value = text.value.split('|')[5];//nvtt_id_hidden
		this.field6.value = text.value.split('|')[0];//customer_id	
		this.field7.value = text.value.split('|')[7];//customer_code_level1_hidden	
		
//		var maKH                          = document.getElementById("maKH");
//		var tenKH                         = document.getElementById("tenKH");
//		var customer_name_level1          = document.getElementById("customer_name_level1");
//		var nvtt_name                     = document.getElementById("nvtt_name");
//		var customer_id_level1_hidden     = document.getElementById("customer_id_level1_hidden");
//		var nvtt_id_hidden                = document.getElementById("nvtt_id_hidden"); 
//		var invoice_name_hidden           = document.getElementById("invoice_name_hidden"); 
	}
	//SETTING is_show_form_lookup = true để tránh việc nhảy field khi nhấn phím qua lại lên xuống.
	is_show_form_lookup = true;
};

_.DATA = function (item/*, input*/) {
	return item; 
};

// Private functions

function Suggestion(data) {
	var o = Array.isArray(data)
	  ? { label: data[0], value: data[1] }
	  : typeof data === "object" && "label" in data && "value" in data ? data : { label: data, value: data };

	this.label = o.label || o.value;
	this.value = o.value;
}
Object.defineProperty(Suggestion.prototype = Object.create(String.prototype), "length", {
	get: function() { return this.label.length; }
});
Suggestion.prototype.toString = Suggestion.prototype.valueOf = function () {
	return "" + this.label;
};

function configure(instance, properties, o) {
	for (var i in properties) {
		var initial = properties[i],
		    attrValue = instance.input.getAttribute("data-" + i.toLowerCase());

		if (typeof initial === "number") {
			instance[i] = parseInt(attrValue);
		}
		else if (initial === false) { // Boolean options must be false by default anyway
			instance[i] = attrValue !== null;
		}
		else if (initial instanceof Function) {
			instance[i] = null;
		}
		else {
			instance[i] = attrValue;
		}

		if (!instance[i] && instance[i] !== 0) {
			instance[i] = (i in o)? o[i] : initial;
		}
	}
}

// Helpers

var slice = Array.prototype.slice;

function $(expr, con) {
	//console.log(expr);
	//console.log(con);
	return typeof expr === "string"? (con || document).querySelector(expr) : expr || null;
}

function $$(expr, con) {
	return slice.call((con || document).querySelectorAll(expr));
}

$.create = function(tag, o) {
	var element = document.createElement(tag);

	for (var i in o) {
		var val = o[i];

		if (i === "inside") {
			$(val).appendChild(element);
		}
		else if (i === "around") {
			var ref = $(val);
			ref.parentNode.insertBefore(element, ref);
			element.appendChild(ref);
		}
		else if (i in element) {
			element[i] = val;
		}
		else {
			element.setAttribute(i, val);
		}
	}

	return element;
};

$.bind = function(element, o) {
	if (element) {
		for (var event in o) {
			var callback = o[event];

			event.split(/\s+/).forEach(function (event) {
				element.addEventListener(event, callback);
			});
		}
	}
};

$.unbind = function(element, o) {
	if (element) {
		for (var event in o) {
			var callback = o[event];

			event.split(/\s+/).forEach(function(event) {
				element.removeEventListener(event, callback);
			});
		}
	}
};

$.fire = function(target, type, properties) {
	var evt = document.createEvent("HTMLEvents");

	evt.initEvent(type, true, true );

	for (var j in properties) {
		evt[j] = properties[j];
	}

	return target.dispatchEvent(evt);
};

$.regExpEscape = function (s) {
	return s.replace(/[-\\^$*+?.()|[\]{}]/g, "\\$&");
};

$.siblingIndex = function (el) {
	/* eslint-disable no-cond-assign */
	for (var i = 0; el = el.previousElementSibling; i++);
	return i;
};

// Initialization

function init() {
	$$("input.awesomplete").forEach(function (input) {
		new _(input);
	});
}

// Are we in a browser? Check for Document constructor
if (typeof Document !== "undefined") {
	// DOM already loaded?
	if (document.readyState !== "loading") {
		init();
	}
	else {
		// Wait for it
		document.addEventListener("DOMContentLoaded", init);
	}
}

_.$ = $;
_.$$ = $$;

// Make sure to export LookupKhachHang on self when in a browser
if (typeof self !== "undefined") {
	self.LookupKhachHang = _;
}

if (typeof self !== "undefined") {
	self.LookupDebitor = _;
}

// Expose LookupKhachHang as a CJS module
if (typeof module === "object" && module.exports) {
	module.exports = _;
}

return _;

}());
function checkParseFloat(val){
	 var kq = parseFloat(val); 
	 if(kq!='NaN'){
		 return false;
	 }
	 return true;
}
function change_alias(alias) {
    var str = alias;
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g,"a"); 
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g,"e"); 
    str = str.replace(/ì|í|ị|ỉ|ĩ/g,"i"); 
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g,"o"); 
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g,"u"); 
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g,"y"); 
    str = str.replace(/đ/g,"d");
    str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'|\"|\&|\#|\[|\]|~|\$|_|`|-|{|}|\||\\/g," ");
    str = str.replace(/ + /g," ");
    str = str.trim(); 
return str;
}