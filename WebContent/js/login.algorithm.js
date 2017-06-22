// encode the input text with base64 algorithm

var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"
		+ "wxyz0123456789+/" + "=";

function encodeBase64(input) {
	input = escape(input);
	var output = "";
	var chr1, chr2, chr3 = "";
	var enc1, enc2, enc3, enc4 = "";
	var i = 0;

	do {
		chr1 = input.charCodeAt(i++);
		chr2 = input.charCodeAt(i++);
		chr3 = input.charCodeAt(i++);

		enc1 = chr1 >> 2;
		enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
		enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
		enc4 = chr3 & 63;

		if (isNaN(chr2)) {
			enc3 = enc4 = 64;
		} else if (isNaN(chr3)) {
			enc4 = 64;
		}

		output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
				+ keyStr.charAt(enc3) + keyStr.charAt(enc4);
		chr1 = chr2 = chr3 = "";
		enc1 = enc2 = enc3 = enc4 = "";
	} while (i < input.length);

	return output;
}

// decode the input text with base64 algorithm
function decode64(input) {
	var output = "";
	var chr1, chr2, chr3 = "";
	var enc1, enc2, enc3, enc4 = "";
	var i = 0;

	// remove all characters that are not A-Z, a-z, 0-9, +, /, or =
	var base64test = /[^A-Za-z0-9\+\/\=]/g;
	if (base64test.exec(input)) {
		alert("There were invalid base64 characters in the input text.\n"
				+ "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n"
				+ "Expect errors in decoding.");
	}
	input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

	do {
		enc1 = keyStr.indexOf(input.charAt(i++));
		enc2 = keyStr.indexOf(input.charAt(i++));
		enc3 = keyStr.indexOf(input.charAt(i++));
		enc4 = keyStr.indexOf(input.charAt(i++));

		chr1 = (enc1 << 2) | (enc2 >> 4);
		chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
		chr3 = ((enc3 & 3) << 6) | enc4;

		output = output + String.fromCharCode(chr1);

		if (enc3 != 64) {
			output = output + String.fromCharCode(chr2);
		}
		if (enc4 != 64) {
			output = output + String.fromCharCode(chr3);
		}

		chr1 = chr2 = chr3 = "";
		enc1 = enc2 = enc3 = enc4 = "";

	} while (i < input.length);

	return unescape(output);
}

function encodeText() {
	// alert(document.cookie);
	var stringConcatenated;
	var rN = Math.floor(Math.random() * 99);
	var rn1 = String.fromCharCode(65 + Math.round(Math.random() * 25));
	var rn2 = String.fromCharCode(65 + Math.round(Math.random() * 25));
	var rn3 = String.fromCharCode(65 + Math.round(Math.random() * 25));
	var rn4 = String.fromCharCode(65 + Math.round(Math.random() * 25));
	var rN5 = Math.floor(Math.random() * 99);
	var rN6 = Math.floor(Math.random() * 999);

	rN = rN + rn1;
	rN = rN + rN6;
	rN = rN + rn2;
	rN = rN + rn3;
	rN = rN + rN5;
	rN = rN + rn4;

	var encodedRn;
	var input;
	input = document.getElementById('password');
	var encodeLevel1;
	encodeLevel1 = encodeBase64(input.value);
	encodedRn = encodeBase64(rN);
	stringConcatenated = encodedRn + encodeLevel1;
	document.getElementById('password').value = stringConcatenated;
	document.getElementById('pageSize').value = rN;

}

function encodeUserId() {
	var input;
	input = document.getElementById('userId');
	encodeLevel1 = encodeBase64(input.value);
	document.getElementById('userId').value = encodeLevel1;
}

// /////////////////////////////////////////////////MD5
// Logic/////////////////////////////////////////////////
function changePasswordMd5Hash() {
	var opassword = document.getElementById('opassword').value;
	var cpassword = document.getElementById('cpassword').value;
	var password = document.getElementById('newpassword').value;

	if(password.length>0)
	{
		   var re = /[A-Z]/;
		   var Stringlen = password.length;
		   var ValidateDigits = /[^0-9]/g;
		   var ValidateSpChar = /[a-zA-Z0-9]/g;
		   var ValidateChar = /[^a-zA-Z]/g;

		   var digitString = password.replace(ValidateDigits , "");
		   var specialString = password.replace(ValidateSpChar, "");
		   var charString = password.replace(ValidateChar, "");

		   if(Stringlen ==0)
		   {
		   document.getElementById('invalidPassword').innerHTML="New Password is required..";
		   document.getElementById('newpassword').value="";
		   document.getElementById('newpassword').focus();
		   return false;
		   }
		   
		   if(Stringlen < 8)
		   {
		   document.getElementById('invalidPassword').innerHTML="New Password must be at least 8 characters";
		   document.getElementById('newpassword').value="";
		   document.getElementById('newpassword').focus();
		   return false;
		   }
		   if(!re.test(password)) {
		   document.getElementById('invalidPassword').innerHTML="Password must contain at least one uppercase letter (A-Z)";
		   document.getElementById('newpassword').focus();
		   document.getElementById('newpassword').value="";
		   return false;
		   }
		   if(specialString < 1)
		   {
		   document.getElementById('invalidPassword').innerHTML="New Password must include at least 1 special (#,@,&,$,=,~,%,* etc) characters";
		   document.getElementById('newpassword').focus();
		   document.getElementById('newpassword').value="";
		   return false;
		   }
		   if(digitString < 1)
		   {
		   document.getElementById('invalidPassword').innerHTML="New Password must include at least 1 numeric characters";
		   document.getElementById('newpassword').focus();
		   document.getElementById('newpassword').value="";
		   return false;
		   }
	}
	
	
	if (opassword.length > 0) {
		var md5opassword = rstr2hex(rstr_md5(str2rstr_utf8(opassword)));
		document.getElementById('opassword').value = md5opassword;
	}
	if (cpassword.length > 0) {
		var md5cpassword = rstr2hex(rstr_md5(str2rstr_utf8(cpassword)));
		document.getElementById('cpassword').value = md5cpassword;
	}

	if (password.length > 0) {
		var md5password = rstr2hex(rstr_md5(str2rstr_utf8(password)));
		document.getElementById('newpassword').value = md5password;
	}
}

function changeOnlyPasswordMd5Hash() {	
	
	var password = document.getElementById('password').value;	
	//alert("password is "+password);

	if(password.length>0)
	{
		   var re = /[A-Z]/;
		   var Stringlen = password.length;
		   var ValidateDigits = /[^0-9]/g;
		   var ValidateSpChar = /[a-zA-Z0-9]/g;
		   var ValidateChar = /[^a-zA-Z]/g;

		   var digitString = password.replace(ValidateDigits , "");
		   var specialString = password.replace(ValidateSpChar, "");
		   var charString = password.replace(ValidateChar, "");

		   if(Stringlen ==0)
		   {
		   document.getElementById('invalidPassword').innerHTML="Password is required..";
		   document.getElementById('password').value="";
		   document.getElementById('password').focus();
		   return false;
		   }
		   
		   if(Stringlen < 8)
		   {
		   document.getElementById('invalidPassword').innerHTML="Password must be at least 8 characters";
		   document.getElementById('password').value="";
		   document.getElementById('password').focus();
		   return false;
		   }
		   if(!re.test(password)) {
		   document.getElementById('invalidPassword').innerHTML="Password must contain at least one uppercase letter (A-Z)";
		   document.getElementById('password').focus();
		   document.getElementById('password').value="";
		   return false;
		   }
		   if(specialString < 1)
		   {
		   document.getElementById('invalidPassword').innerHTML="Password must include at least 1 special (#,@,&,$,=,~,%,* etc) characters";
		   document.getElementById('password').focus();
		   document.getElementById('password').value="";
		   return false;
		   }
		   if(digitString < 1)
		   {
		   document.getElementById('invalidPassword').innerHTML="Password must include at least 1 numeric characters";
		   document.getElementById('password').focus();
		   document.getElementById('password').value="";
		   return false;
		   }
	}	

	if (password.length > 0) {
		var md5password = rstr2hex(rstr_md5(str2rstr_utf8(password)));
		document.getElementById('password').value = md5password;
	}
}

function resetPasswordMd5Hash() {
	
   var cpassword = document.getElementById('cpassword').value;
   var password = document.getElementById('password').value;
	   
   var re = /[A-Z]/;
   var Stringlen = password.length;
   var ValidateDigits = /[^0-9]/g;
   var ValidateSpChar = /[a-zA-Z0-9]/g;
   var ValidateChar = /[^a-zA-Z]/g;

   var digitString = password.replace(ValidateDigits , "");
   var specialString = password.replace(ValidateSpChar, "");
   var charString = password.replace(ValidateChar, "");

   if(Stringlen ==0)
   {
   document.getElementById('invalidPassword').innerHTML="New Password is required";
   document.getElementById('password').value="";
   document.getElementById('password').focus();
   return false;
   }
   
   if(Stringlen < 8)
   {
   document.getElementById('invalidPassword').innerHTML="New Password must be at least 8 characters";
   document.getElementById('password').value="";
   document.getElementById('password').focus();
   return false;
   }
   if(!re.test(password)) {
   document.getElementById('invalidPassword').innerHTML="New Password must contain at least one uppercase letter (A-Z)";
   document.getElementById('password').focus();
   document.getElementById('password').value="";
   return false;
   }
   if(specialString < 1)
   {
   document.getElementById('invalidPassword').innerHTML="New Password must include at least 1 special (#,@,&,$,=,<,>,~,%,* etc) characters";
   document.getElementById('password').focus();
   document.getElementById('password').value="";
   return false;
   }
   if(digitString < 1)
   {
   document.getElementById('invalidPassword').innerHTML="New Password must include at least 1 numeric characters";
   document.getElementById('password').focus();
   document.getElementById('password').value="";
   return false;
   }

	if (cpassword.length > 0) {
		var md5cpassword = rstr2hex(rstr_md5(str2rstr_utf8(cpassword)));
		document.getElementById('cpassword').value = md5cpassword;
	}

	if (password.length > 0) {
		var md5password = rstr2hex(rstr_md5(str2rstr_utf8(password)));
		document.getElementById('password').value = md5password;
	}
}

// MD5 Algorithm
function encodeInputText() {
	if (document.getElementById('password').value.length > 0) {
		var randomNumber = document.getElementById('rndToken').value;
		var password = document.getElementById('password').value;
		var level1PasswordMD5 = rstr2hex(rstr_md5(str2rstr_utf8(password)));
		var combinedTextMD5 = randomNumber + level1PasswordMD5;
		var level2PasswordMD5 = rstr2hex(rstr_md5(str2rstr_utf8(combinedTextMD5)));
		document.getElementById('password').value = level2PasswordMD5;
		document.getElementById('rndToken').value = "";
		//encodeUserId();
	}
}

//MD5 Algorithm
function encodeUserPassword() {
	if (document.getElementById('userPassword').value.length > 0) {
		var level1PasswordMD5 = rstr2hex(rstr_md5(str2rstr_utf8(document.getElementById('userPassword').value)));
		document.getElementById('userPassword').value = level1PasswordMD5;
		//encodeUserId();
	}
}

/*
 * Calculate the MD5 of a raw string
 */
function rstr_md5(s) {
	return binl2rstr(binl_md5(rstr2binl(s), s.length * 8));
}

/*
 * Calculate the HMAC-MD5, of a key and some data (raw strings)
 */
function rstr_hmac_md5(key, data) {
	var bkey = rstr2binl(key);
	if (bkey.length > 16)
		bkey = binl_md5(bkey, key.length * 8);

	var ipad = Array(16), opad = Array(16);
	for ( var i = 0; i < 16; i++) {
		ipad[i] = bkey[i] ^ 0x36363636;
		opad[i] = bkey[i] ^ 0x5C5C5C5C;
	}

	var hash = binl_md5(ipad.concat(rstr2binl(data)), 512 + data.length * 8);
	return binl2rstr(binl_md5(opad.concat(hash), 512 + 128));
}

/*
 * Convert a raw string to a hex string
 */
function rstr2hex(input) {
	try {
		hexcase
	} catch (e) {
		hexcase = 0;
	}
	var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
	var output = "";
	var x;
	for ( var i = 0; i < input.length; i++) {
		x = input.charCodeAt(i);
		output += hex_tab.charAt((x >>> 4) & 0x0F) + hex_tab.charAt(x & 0x0F);
	}
	return output;
}

/*
 * Convert a raw string to a base-64 string
 */
function rstr2b64(input) {
	try {
		b64pad
	} catch (e) {
		b64pad = '';
	}
	var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var output = "";
	var len = input.length;
	for ( var i = 0; i < len; i += 3) {
		var triplet = (input.charCodeAt(i) << 16)
				| (i + 1 < len ? input.charCodeAt(i + 1) << 8 : 0)
				| (i + 2 < len ? input.charCodeAt(i + 2) : 0);
		for ( var j = 0; j < 4; j++) {
			if (i * 8 + j * 6 > input.length * 8)
				output += b64pad;
			else
				output += tab.charAt((triplet >>> 6 * (3 - j)) & 0x3F);
		}
	}
	return output;
}

/*
 * Convert a raw string to an arbitrary string encoding
 */
function rstr2any(input, encoding) {
	var divisor = encoding.length;
	var i, j, q, x, quotient;

	/* Convert to an array of 16-bit big-endian values, forming the dividend */
	var dividend = Array(Math.ceil(input.length / 2));
	for (i = 0; i < dividend.length; i++) {
		dividend[i] = (input.charCodeAt(i * 2) << 8)
				| input.charCodeAt(i * 2 + 1);
	}

	/*
	 * Repeatedly perform a long division. The binary array forms the dividend,
	 * the length of the encoding is the divisor. Once computed, the quotient
	 * forms the dividend for the next step. All remainders are stored for later
	 * use.
	 */
	var full_length = Math.ceil(input.length * 8
			/ (Math.log(encoding.length) / Math.log(2)));
	var remainders = Array(full_length);
	for (j = 0; j < full_length; j++) {
		quotient = Array();
		x = 0;
		for (i = 0; i < dividend.length; i++) {
			x = (x << 16) + dividend[i];
			q = Math.floor(x / divisor);
			x -= q * divisor;
			if (quotient.length > 0 || q > 0)
				quotient[quotient.length] = q;
		}
		remainders[j] = x;
		dividend = quotient;
	}

	/* Convert the remainders to the output string */
	var output = "";
	for (i = remainders.length - 1; i >= 0; i--)
		output += encoding.charAt(remainders[i]);

	return output;
}

/*
 * Encode a string as utf-8. For efficiency, this assumes the input is valid
 * utf-16.
 */
function str2rstr_utf8(input) {
	var output = "";
	var i = -1;
	var x, y;

	while (++i < input.length) {
		/* Decode utf-16 surrogate pairs */
		x = input.charCodeAt(i);
		y = i + 1 < input.length ? input.charCodeAt(i + 1) : 0;
		if (0xD800 <= x && x <= 0xDBFF && 0xDC00 <= y && y <= 0xDFFF) {
			x = 0x10000 + ((x & 0x03FF) << 10) + (y & 0x03FF);
			i++;
		}

		/* Encode output as utf-8 */
		if (x <= 0x7F)
			output += String.fromCharCode(x);
		else if (x <= 0x7FF)
			output += String.fromCharCode(0xC0 | ((x >>> 6) & 0x1F),
					0x80 | (x & 0x3F));
		else if (x <= 0xFFFF)
			output += String.fromCharCode(0xE0 | ((x >>> 12) & 0x0F),
					0x80 | ((x >>> 6) & 0x3F), 0x80 | (x & 0x3F));
		else if (x <= 0x1FFFFF)
			output += String.fromCharCode(0xF0 | ((x >>> 18) & 0x07),
					0x80 | ((x >>> 12) & 0x3F), 0x80 | ((x >>> 6) & 0x3F),
					0x80 | (x & 0x3F));
	}
	return output;
}

/*
 * Encode a string as utf-16
 */
function str2rstr_utf16le(input) {
	var output = "";
	for ( var i = 0; i < input.length; i++)
		output += String.fromCharCode(input.charCodeAt(i) & 0xFF, (input
				.charCodeAt(i) >>> 8) & 0xFF);
	return output;
}

function str2rstr_utf16be(input) {
	var output = "";
	for ( var i = 0; i < input.length; i++)
		output += String.fromCharCode((input.charCodeAt(i) >>> 8) & 0xFF, input
				.charCodeAt(i) & 0xFF);
	return output;
}

/*
 * Convert a raw string to an array of little-endian words Characters >255 have
 * their high-byte silently ignored.
 */
function rstr2binl(input) {
	var output = Array(input.length >> 2);
	for ( var i = 0; i < output.length; i++)
		output[i] = 0;
	for ( var i = 0; i < input.length * 8; i += 8)
		output[i >> 5] |= (input.charCodeAt(i / 8) & 0xFF) << (i % 32);
	return output;
}

/*
 * Convert an array of little-endian words to a string
 */
function binl2rstr(input) {
	var output = "";
	for ( var i = 0; i < input.length * 32; i += 8)
		output += String.fromCharCode((input[i >> 5] >>> (i % 32)) & 0xFF);
	return output;
}

/*
 * Calculate the MD5 of an array of little-endian words, and a bit length.
 */
function binl_md5(x, len) {
	/* append padding */
	x[len >> 5] |= 0x80 << ((len) % 32);
	x[(((len + 64) >>> 9) << 4) + 14] = len;

	var a = 1732584193;
	var b = -271733879;
	var c = -1732584194;
	var d = 271733878;

	for ( var i = 0; i < x.length; i += 16) {
		var olda = a;
		var oldb = b;
		var oldc = c;
		var oldd = d;

		a = md5_ff(a, b, c, d, x[i + 0], 7, -680876936);
		d = md5_ff(d, a, b, c, x[i + 1], 12, -389564586);
		c = md5_ff(c, d, a, b, x[i + 2], 17, 606105819);
		b = md5_ff(b, c, d, a, x[i + 3], 22, -1044525330);
		a = md5_ff(a, b, c, d, x[i + 4], 7, -176418897);
		d = md5_ff(d, a, b, c, x[i + 5], 12, 1200080426);
		c = md5_ff(c, d, a, b, x[i + 6], 17, -1473231341);
		b = md5_ff(b, c, d, a, x[i + 7], 22, -45705983);
		a = md5_ff(a, b, c, d, x[i + 8], 7, 1770035416);
		d = md5_ff(d, a, b, c, x[i + 9], 12, -1958414417);
		c = md5_ff(c, d, a, b, x[i + 10], 17, -42063);
		b = md5_ff(b, c, d, a, x[i + 11], 22, -1990404162);
		a = md5_ff(a, b, c, d, x[i + 12], 7, 1804603682);
		d = md5_ff(d, a, b, c, x[i + 13], 12, -40341101);
		c = md5_ff(c, d, a, b, x[i + 14], 17, -1502002290);
		b = md5_ff(b, c, d, a, x[i + 15], 22, 1236535329);

		a = md5_gg(a, b, c, d, x[i + 1], 5, -165796510);
		d = md5_gg(d, a, b, c, x[i + 6], 9, -1069501632);
		c = md5_gg(c, d, a, b, x[i + 11], 14, 643717713);
		b = md5_gg(b, c, d, a, x[i + 0], 20, -373897302);
		a = md5_gg(a, b, c, d, x[i + 5], 5, -701558691);
		d = md5_gg(d, a, b, c, x[i + 10], 9, 38016083);
		c = md5_gg(c, d, a, b, x[i + 15], 14, -660478335);
		b = md5_gg(b, c, d, a, x[i + 4], 20, -405537848);
		a = md5_gg(a, b, c, d, x[i + 9], 5, 568446438);
		d = md5_gg(d, a, b, c, x[i + 14], 9, -1019803690);
		c = md5_gg(c, d, a, b, x[i + 3], 14, -187363961);
		b = md5_gg(b, c, d, a, x[i + 8], 20, 1163531501);
		a = md5_gg(a, b, c, d, x[i + 13], 5, -1444681467);
		d = md5_gg(d, a, b, c, x[i + 2], 9, -51403784);
		c = md5_gg(c, d, a, b, x[i + 7], 14, 1735328473);
		b = md5_gg(b, c, d, a, x[i + 12], 20, -1926607734);

		a = md5_hh(a, b, c, d, x[i + 5], 4, -378558);
		d = md5_hh(d, a, b, c, x[i + 8], 11, -2022574463);
		c = md5_hh(c, d, a, b, x[i + 11], 16, 1839030562);
		b = md5_hh(b, c, d, a, x[i + 14], 23, -35309556);
		a = md5_hh(a, b, c, d, x[i + 1], 4, -1530992060);
		d = md5_hh(d, a, b, c, x[i + 4], 11, 1272893353);
		c = md5_hh(c, d, a, b, x[i + 7], 16, -155497632);
		b = md5_hh(b, c, d, a, x[i + 10], 23, -1094730640);
		a = md5_hh(a, b, c, d, x[i + 13], 4, 681279174);
		d = md5_hh(d, a, b, c, x[i + 0], 11, -358537222);
		c = md5_hh(c, d, a, b, x[i + 3], 16, -722521979);
		b = md5_hh(b, c, d, a, x[i + 6], 23, 76029189);
		a = md5_hh(a, b, c, d, x[i + 9], 4, -640364487);
		d = md5_hh(d, a, b, c, x[i + 12], 11, -421815835);
		c = md5_hh(c, d, a, b, x[i + 15], 16, 530742520);
		b = md5_hh(b, c, d, a, x[i + 2], 23, -995338651);

		a = md5_ii(a, b, c, d, x[i + 0], 6, -198630844);
		d = md5_ii(d, a, b, c, x[i + 7], 10, 1126891415);
		c = md5_ii(c, d, a, b, x[i + 14], 15, -1416354905);
		b = md5_ii(b, c, d, a, x[i + 5], 21, -57434055);
		a = md5_ii(a, b, c, d, x[i + 12], 6, 1700485571);
		d = md5_ii(d, a, b, c, x[i + 3], 10, -1894986606);
		c = md5_ii(c, d, a, b, x[i + 10], 15, -1051523);
		b = md5_ii(b, c, d, a, x[i + 1], 21, -2054922799);
		a = md5_ii(a, b, c, d, x[i + 8], 6, 1873313359);
		d = md5_ii(d, a, b, c, x[i + 15], 10, -30611744);
		c = md5_ii(c, d, a, b, x[i + 6], 15, -1560198380);
		b = md5_ii(b, c, d, a, x[i + 13], 21, 1309151649);
		a = md5_ii(a, b, c, d, x[i + 4], 6, -145523070);
		d = md5_ii(d, a, b, c, x[i + 11], 10, -1120210379);
		c = md5_ii(c, d, a, b, x[i + 2], 15, 718787259);
		b = md5_ii(b, c, d, a, x[i + 9], 21, -343485551);

		a = safe_add(a, olda);
		b = safe_add(b, oldb);
		c = safe_add(c, oldc);
		d = safe_add(d, oldd);
	}
	return Array(a, b, c, d);
}

/*
 * These functions implement the four basic operations the algorithm uses.
 */
function md5_cmn(q, a, b, x, s, t) {
	return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s), b);
}
function md5_ff(a, b, c, d, x, s, t) {
	return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function md5_gg(a, b, c, d, x, s, t) {
	return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function md5_hh(a, b, c, d, x, s, t) {
	return md5_cmn(b ^ c ^ d, a, b, x, s, t);
}
function md5_ii(a, b, c, d, x, s, t) {
	return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally to
 * work around bugs in some JS interpreters.
 */
function safe_add(x, y) {
	var lsw = (x & 0xFFFF) + (y & 0xFFFF);
	var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
	return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left.
 */
function bit_rol(num, cnt) {
	return (num << cnt) | (num >>> (32 - cnt));
}

// function to disable the auto complete text box for userid and password
function offAutoComplete() {
	document.getElementById('userId').setAttribute('autocomplete', 'off');
}

/*
 * function to disable the auto complete feature of school directory edit form
 * fields.
 */
function offAutoCompleteForSchoolEdit() {
	if(document.getElementById('schoolNameRural')!=null)
	{
		document.getElementById('schoolNameRural').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('schoolAddressRural')!=null)
	{
		document.getElementById('schoolAddressRural').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('schoolCodeRural')!=null)
	{
		document.getElementById('schoolCodeRural').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('schoolCategoryRural')!=null)
	{
		document.getElementById('schoolCategoryRural').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('captchaRural')!=null)
	{
		document.getElementById('captchaRural').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('schoolNameUrban')!=null)
	{
		document.getElementById('schoolNameUrban').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('schoolAddressUrban')!=null)
	{
		document.getElementById('schoolAddressUrban').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('schoolCodeUrban')!=null)
	{
		document.getElementById('schoolCodeUrban').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('schoolCategoryUrban')!=null)
	{
		document.getElementById('schoolCategoryUrban').setAttribute('autocomplete', 'off');
	}
	if(document.getElementById('captchaUrban')!=null)
	{
		document.getElementById('captchaUrban').setAttribute('autocomplete', 'off');
	}	
	
}

function encodePassword() 
{
    var cpassword=document.getElementById('cpassword').value;
    var password=document.getElementById('userMaster.userPassword').value;
	var re = /[A-Z]/;
	var Stringlen = password.length;
	var ValidateDigits = /[^0-9]/g;
	var ValidateSpChar = /[a-zA-Z0-9]/g;
	var ValidateChar = /[^a-zA-Z]/g;
	
	var digitString = password.replace(ValidateDigits , "");
	var specialString = password.replace(ValidateSpChar, "");
	var charString = password.replace(ValidateChar, "");
	
	if(Stringlen ==0)
	{
	document.getElementById('invalidPassword').innerHTML="Password is required";
	document.getElementById('userMaster.userPassword').value="";
	document.getElementById('userMaster.userPassword').focus();
	return false;
	}
	
	if(Stringlen < 8)
	{
	document.getElementById('invalidPassword').innerHTML="Password must be at least 8 characters";
	document.getElementById('userMaster.userPassword').value="";
	document.getElementById('userMaster.userPassword').focus();
	return false;
	}
	if(!re.test(password)) {
	document.getElementById('invalidPassword').innerHTML="Password must contain at least one uppercase letter (A-Z)";
	document.getElementById('userMaster.userPassword').focus();
	document.getElementById('userMaster.userPassword').value="";
	return false;
	}
	if(specialString < 1)
	{
	document.getElementById('invalidPassword').innerHTML="Password must include at least 1 special (#,@,&,$,=,<,>,~,%,* etc) characters";
	document.getElementById('userMaster.userPassword').focus();
	document.getElementById('userMaster.userPassword').value="";
	return false;
	}
	if(digitString < 1)
	{
	document.getElementById('invalidPassword').innerHTML="Password must include at least 1 numeric characters";
	document.getElementById('userMaster.userPassword').focus();
	document.getElementById('userMaster.userPassword').value="";
    return false;
    }
	if(password.length>0){
	var md5password = rstr2hex(rstr_md5(str2rstr_utf8(password)));
	document.getElementById('userMaster.userPassword').value = md5password;
	}
	if(cpassword.length>0){
		var md5password = rstr2hex(rstr_md5(str2rstr_utf8(cpassword)));
		document.getElementById('cpassword').value = md5password;
		}
	
	/*
	 * function to disable the auto complete feature of VIF fields.
	 */
	
	function offAutoCompleteForVIF() 
	{
		if(document.getElementById('numOfHabInVill')!=null)
		{
			document.getElementById('numOfHabInVill').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingPriStageSch')!=null)
		{
			document.getElementById('numOfHabHavingPriStageSch').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingPriStageSchWithin1Km')!=null)
		{
			document.getElementById('numOfHabHavingPriStageSchWithin1Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingPriStageSchBeyond1Km')!=null)
		{
			document.getElementById('numOfHabHavingPriStageSchBeyond1Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingUppPriStageSch')!=null)
		{
			document.getElementById('numOfHabHavingUppPriStageSch').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingUppPriStageSchWithin3Km')!=null)
		{
			document.getElementById('numOfHabHavingUppPriStageSchWithin3Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingUppPriStageSchBeyond3Km')!=null)
		{
			document.getElementById('numOfHabHavingUppPriStageSchBeyond3Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfUnrecSchHavingPriUppPriClasses')!=null)
		{
			document.getElementById('numOfUnrecSchHavingPriUppPriClasses').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numofEnroInUnrecoSchPriStage')!=null)
		{
			document.getElementById('numofEnroInUnrecoSchPriStage').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numofEnroInUnrecoSchUppPriStage')!=null)
		{
			document.getElementById('numofEnroInUnrecoSchUppPriStage').setAttribute('autocomplete', 'off');
		}	
		
	}
	
   
} 

function encodePassword1() 
{
	
	//alert("When Data-User");
    var cpassword=document.getElementById('cpassword').value;
    var password=document.getElementById('dataUserMaster.userPassword').value;
	var re = /[A-Z]/;
	var Stringlen = password.length;
	var ValidateDigits = /[^0-9]/g;
	var ValidateSpChar = /[a-zA-Z0-9]/g;
	var ValidateChar = /[^a-zA-Z]/g;
	
	var digitString = password.replace(ValidateDigits , "");
	var specialString = password.replace(ValidateSpChar, "");
	var charString = password.replace(ValidateChar, "");
	
	if(Stringlen ==0)
	{
	document.getElementById('invalidPassword').innerHTML="Password is required";
	document.getElementById('dataUserMaster.userPassword').value="";
	document.getElementById('dataUserMaster.userPassword').focus();
	return false;
	}
	
	if(Stringlen < 8)
	{
	document.getElementById('invalidPassword').innerHTML="Password must be at least 8 characters";
	document.getElementById('dataUserMaster.userPassword').value="";
	document.getElementById('dataUserMaster.userPassword').focus();
	return false;
	}
	if(!re.test(password)) {
	document.getElementById('invalidPassword').innerHTML="Password must contain at least one uppercase letter (A-Z)";
	document.getElementById('dataUserMaster.userPassword').focus();
	document.getElementById('dataUserMaster.userPassword').value="";
	return false;
	}
	if(specialString < 1)
	{
	document.getElementById('invalidPassword').innerHTML="Password must include at least 1 special (#,@,&,$,=,<,>,~,%,* etc) characters";
	document.getElementById('dataUserMaster.userPassword').focus();
	document.getElementById('dataUserMaster.userPassword').value="";
	
	return false;
	}
	if(digitString < 1)
	{
	document.getElementById('invalidPassword').innerHTML="Password must include at least 1 numeric characters";
	document.getElementById('dataUserMaster.userPassword').focus();
	document.getElementById('dataUserMaster.userPassword').value="";
    return false;
    }
	if(password.length>0){
	var md5password = rstr2hex(rstr_md5(str2rstr_utf8(password)));
	document.getElementById('dataUserMaster.userPassword').value = md5password;
	}
	if(cpassword.length>0){
		var md5password = rstr2hex(rstr_md5(str2rstr_utf8(cpassword)));
		document.getElementById('cpassword').value = md5password;
	}
	
	
	/*
	 * function to disable the auto complete feature of VIF fields.
	 */
	
	function offAutoCompleteForVIF() 
	{
		if(document.getElementById('numOfHabInVill')!=null)
		{
			document.getElementById('numOfHabInVill').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingPriStageSch')!=null)
		{
			document.getElementById('numOfHabHavingPriStageSch').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingPriStageSchWithin1Km')!=null)
		{
			document.getElementById('numOfHabHavingPriStageSchWithin1Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingPriStageSchBeyond1Km')!=null)
		{
			document.getElementById('numOfHabHavingPriStageSchBeyond1Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingUppPriStageSch')!=null)
		{
			document.getElementById('numOfHabHavingUppPriStageSch').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingUppPriStageSchWithin3Km')!=null)
		{
			document.getElementById('numOfHabHavingUppPriStageSchWithin3Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfHabHavingUppPriStageSchBeyond3Km')!=null)
		{
			document.getElementById('numOfHabHavingUppPriStageSchBeyond3Km').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numOfUnrecSchHavingPriUppPriClasses')!=null)
		{
			document.getElementById('numOfUnrecSchHavingPriUppPriClasses').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numofEnroInUnrecoSchPriStage')!=null)
		{
			document.getElementById('numofEnroInUnrecoSchPriStage').setAttribute('autocomplete', 'off');
		}
		if(document.getElementById('numofEnroInUnrecoSchUppPriStage')!=null)
		{
			document.getElementById('numofEnroInUnrecoSchUppPriStage').setAttribute('autocomplete', 'off');
		}	
		
	}
}
