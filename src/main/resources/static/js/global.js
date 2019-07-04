$(document).ready(function() {
	fixGitHover();
	$('.site-entrance').height(screen.height);
	animateWelcome();
	animateGoDigitalOnClick();
	minimizeHeaderOnScroll();
	maximizeHeaderOnClickMenu();
	expandLineOnFocus();
	setupLoginAndRegPage();
	validateLogAndRegOnClick();
});

var fixGitHover = function() {
	$('.github').hover(function() {
		$('.github .icon-mark').css({
			background : 'transparent',
			color : '#ff0031'
		});
	});
	$('.github').mouseleave(function() {
		$('.github .icon-mark').css({
			color : '#ffffff'
		});
	});
};

var animateWelcome = function() {
	$('.welcome').css(
			{
				top : ((screen.height - $('.nav-header').height()) - $(
						'.welcome').height())
						/ 2 + "px"
			});
	$('.welcome').hide();
	$('.welcome').fadeIn(3000);
};

var animateGoDigitalOnClick = function() {
	$('a[href^="#"]').on('click', function(event) {
		var target = $(this.getAttribute('href'));
		if (target.length) {
			event.preventDefault();
			$('html, body').stop().animate({
				scrollTop : target.offset().top
			}, 3000);
		}
	});
};

var minimizeHeaderOnScroll = function() {
	$('.menu').hide();
	$(window).scroll(function() {
		if ($('html, body').scrollTop() >= $('#services').offset().top) {
			$('.nav-header').hide();
			$('.menu').slideDown();
		} else {
			$('.menu').click();
		}
	});
};

var maximizeHeaderOnClickMenu = function() {
	$('.menu').click(function() {
		$('.menu').hide();
		$('.nav-header').slideDown();
	});
};

var expandLineOnFocus = function() {
	var box = [ '#name', '#email-id', '#feed-msg', '#usernameReg',
			'#passwordLogin', '#firstname', '#lastname', '#email',
			'#passwordReg', '#centreCode', '#centreName', '#regionalCode' ];
	$('.focus-expand').hide();
	$.each(box, function(index, element) {
		$(element).focus(function() {
			$(element + '+ div > .focus-expand').show("slide", {
				direction : "left"
			}, 1000);
		});
		$(element).blur(function() {
			$(element + '+ div > .focus-expand').hide("slide", {
				direction : "left"
			}, 1000);
		});
	});
};

var animateReg = function() {
	$('.right-box-vh > *:lt(3)').each(function(index, elem) {
		$(this).hide();
	});
	$('#login').hide();
	$('#register').show();
	$('.left-box-vh').hide();
	$('.right-box-vh').animate({
		left : '0px',
		width : '60%'
	}, "slow").animate({
		width : '30%'
	}, "slow", function() {
		$('.right-box-vh > *:gt(2)').each(function(index, elem) {
			$(this).show("fade", 800);
		});
	});
	$('.left-box-vh').animate({
		left : '30%'
	}, "slow", function() {
		$('.left-box-vh').show("fade", 500);
	});
};

var animateLog = function() {
	$('.right-box-vh > *:gt(2)').each(function(index, elem) {
		$(this).hide();
	});
	$('#login').show();
	$('#register').hide();
	$('.left-box-vh').hide();
	$('.right-box-vh').animate({
		left : '40%',
		width : '60%'
	}, "slow").animate({
		left : '70%',
		width : '30%'
	}, "slow", function() {
		$('.right-box-vh > *:lt(3)').each(function(index, elem) {
			$(this).show("fade", 800);
		});
	});
	$('.left-box-vh').animate({
		left : '0px'
	}, "slow", function() {
		$('.left-box-vh').show("fade", 500);
	});
};

var setupLoginAndRegPage = function() {
	$('.right-box-vh > *:gt(2)').each(function(index, elem) {
		$(this).hide();
	});
	$('#register').hide();
	$('.reg-button').click(animateReg);
	$('.log-button').click(animateLog);
};

var validateLogin = function() {
	$('#login-button')
			.click(
					function() {
						var msg;
						var username = $('#usernameLogin').val();
						var password = $('#passwordLogin').val();
						var userPatt = /^[A-Za-z][A-za-z0-9_]+$/;
						var passPatt = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
						if (username === null || password === null
								|| username.length === 0
								|| password.length === 0) {
							msg = "Username or Password cannot be left blank.";
						} else if (!userPatt.test(username)) {
							msg = "Username must start with alphabet followed by alpha-numeric or underscore(_) character.";
						} else if (!passPatt.test(password)) {
							msg = "Password must have 8 to 16 characters and should contain atleast one number and one special character.";
						} else {
							$('#login').submit();
						}
						if (msg !== null) {
							if ($('#login-error').length === 0) {
								$('#login-btn-container').before(
										"<p id='login-error'>Error: " + msg
												+ "</p>");
							} else {
								$('#login-error').html(msg);
							}
						}
					});
};

var validateRegister = function() {
	$('#register-button')
			.click(
					function() {
						var msg;
						var firstname = $('#firstname').val();
						var lastname = $('#lastname').val();
						var emailId = $('#email').val();
						var username = $('#usernameReg').val();
						var password = $('#passwordReg').val();
						var centreCode = $('#centreCode').val();
						;
						var centreName = $('#centreName').val();
						;
						var regionalCode = $('#regionalCode').val();
						;
						var namePatt = /^[A-za-z]+$/;
						var emailPatt = /^[A-Z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Z]{2,4}$/i;
						var userPatt = /^[A-Za-z][A-za-z0-9_]+$/;
						var passPatt = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
						if (firstname === null || lastname === null
								|| emailId === null || password === null
								|| username === null || centreCode === null
								|| centreName === null || regionalCode === null
								|| firstname.length === 0
								|| lastname.length === 0
								|| emailId.length === 0
								|| username.length === 0
								|| password.length === 0
								|| centreCode.length === 0
								|| centreName.length === 0
								|| regionalCode.length === 0) {
							msg = "None of the field marked with asterisk(*) can be left empty.";
						} else if (!namePatt.test(firstname)
								|| !namePatt.test(lastname)) {
							msg = "Firstname and Lastname must contain only alphabets.";
						} else if (!emailPatt.test(emailId)) {
							msg = "Please enter a valid email address.";
						} else if (!userPatt.test(username)) {
							msg = "Username must start with alphabet followed by alpha-numeric or underscore(_) character.";
						} else if (!passPatt.test(password)) {
							msg = "Password must have 8 to 16 characters and should contain atleast one number and one special character.";
						} else {
							$('#register').submit();
						}
						if (msg !== null) {
							if ($('#reg-error').length === 0) {
								$('#register-btn-container').before(
										"<p id='reg-error'>Error: " + msg
												+ "</p>");
							} else {
								$('#reg-error').html("Error: " + msg);
							}
						}
					});
};

var validateLogAndRegOnClick = function() {
	validateLogin();
	validateRegister();
};