/**
 * @file public JavaScript file for public operations
 *
 * @author Sam Yang (samyangcoder@gmail.com)
 * @copyright Sam Yang 2017
 * @version v2.1.0
 * @license MIT
 *
 * Notice: You should import JQuery before this file.
 */

'use strict';

// public properties for wide usage
const PUBLIC = {
  imgBasePath: 'http://otsjjmye1.bkt.clouddn.com/', // qiniu image base url
  requestUrl: 'http://112.74.44.89:8080/back_end', // request data base url
};

/*
 * request data from back end.
 *
 * Examples:
 *
 *    <form id="form">
 *      <input type="text" name="username">
 *      <input type="file" name="file">
 *      <button id="upload" type="button">upload</button>
 *    </form>
 *
 *    const fd = new FormData($('#form')[0]);
 *    // const fd = new FormData();
 *    // fd.append('username', 'Sam Yang');
 *    // fd.append('file', $('input[name="file"]')[0].files[0]);
 *
 *    const params = {
 *      url: `${PUBLIC.requestUrl}/user/login`,
 *      method: 'POST',
 *      data: fd,
 *    };
 *    RequestData.getData(params.url, params.method, params.data, function(res, status[, message]) {
 *      // res is the data return from server
 *      // status is a string describing the status of the response of the request
 *      // if `status === 'error'`, message is a string describing the reason why error happens, otherwise ''
 *      // here you can place your bussiness code
 *    });
 *
 */
class RequestData {
  // @param {string} url - Request url
  // @param {string} method - The HTTP method to use for the request
  // @param {[FormData object]|object} data - Data to be sent to the server
  // @param {function} callback - A function to be called after request succeeds or fail. The function gets passed two arguments
  //        => {object} resData - The data returned from the server
  //        => {string} textStatus - A string describing the status of the response of the request
  //        => {string|object} [errorMessage] - A string or an error object describing the reason why error happens
  static getData(url, method, data, callback) {
    const isFd = data instanceof FormData;
    const contentType = isFd ? false : 'application/x-www-form-urlencoded';
    const processData = !isFd;

    $.ajax({
      url, // Request url
      method: method.toUpperCase(), // The HTTP method to use for the request
      async: true, // default: true (all requests are sent asynchronously)
      contentType, // default: 'application/x-www-form-urlencoded; charset=UTF-8'. The content type of HTTP request header
      dataType: 'json', // The type of data that you're expecting back from the server
      data, // Data to be sent to the server
      beforeSend(jqXHR, settings) {
        // ...
      },
      success(resData, textStatus, jqXHR) { // A function to be called if the request succeeds.
        callback(resData, textStatus, '');
      },
      error(jqXHR, textStatus, errorMessage) { // A function to be called if the request fails
        const resData = {};
        errorMessage = errorMessage.message || errorMessage;

        callback(resData, textStatus, errorMessage);
      },
      complete(jqXHR, textStatus) { // A function to be called when the request finishes (after success and error cb)
        // ...
      },
      timeout: 10000, // Set a timeout (in milliseconds) for the request
      processData, // Prevent data passed in to the data option as an object being processed and transformed into a query string
    });
  }
}

/*
 * warp Storage API, store some data in localStorage.
 *
 *  Examples:
 *
 *    const isLogin = Storage.get('isLogin');
 *    Storage.set('isLogin', 'false');
 *
 */
class Storage {
  // @param {string} key
  static get(key) {
    return window.localStorage.getItem(key);
  }

  // @param {string} key
  // @parm {string} value
  static set(key, value) {
    window.localStorage.setItem(key, value);
  }

  // clear all item from localStorage
  static clear() {
    window.localStorage.clear();
  }

  // remote one item from localStorage
  // @param {string} key
  static rm(key) {
    window.localStorage.removeItem(key);
  }
}

/*
 * render dom with data got from back end.
 *
 *  Examples:
 *
 *    Render.publicInit();
 *
 */
class Render {
  static publicInit() {
    Render.addNavbar();
    Render.addFooter();
    $('head').append('<link rel="shortcut icon" href="../assets/img/favicon.ico">');
  }

  // add nav bar to each page of front end
  static addNavbar() {
    const userType = +Storage.get('type'); // {number}, `0` for `person` user and `1` for `enterprise` user
    const isLogin = Storage.get('login') === 'true'; // {boolean}, `true` for login and otherwise `false`
    const avatarSrc = Storage.get('avatar'); // the url of user avatar
    const nickname = Storage.get('name');
    const header = $('header');
    const isPersonUser = !userType;
    const signBox = `
      <li class="nav-right btn">
        <a href="./signup.html">注册</a>
      </li>
      <li class="divider nav-right">
        |
      </li>
      <li class="nav-right btn">
        <a href="./signin.html">登录</a>
      </li>
    `;
    const avatarBox = `
      <li class="nav-right info-box">
        <a href="javascript:;" class="signout"><i class="fa fa-sign-out fa-2x" aria-hidden="true"></i></a>
      </li>
      <li class="nav-right info-box nickname-box">
        <span class="nickname">${nickname}</span>
      </li>
      <li class="nav-right avatar-box">
        <a href="${isPersonUser
                        ? './personal-user.html'
                        : './enterprise-user.html'
                  }"><img src="${PUBLIC.imgBasePath}${avatarSrc}" class="avatar"></a>
      </li>
    `;
    const nav = `
      <nav>
        <div class="nav-icon">
          <i class="fa fa-bars fa-lg" aria-hidden="true"></i>
        </div>
        <ul>
          <li>
            <a href="./index.html">
              <i class="fa fa-ship fa-lg" aria-hidden="true"></i>
              <h2 class="nav-head">远航</h2>
            </a>
          </li>
          <li class="btn">
            <a href="./job-market.html">求职市场</a>
          </li>
          <li class="btn">
            <a href="./talent-market.html">人才市场</a>
          </li>
          <li class="btn">
            <a href="./career-guidance.html">职业指导</a>
          </li>
          ${isLogin ? avatarBox : signBox}
        </ul>
      </nav>
    `;

    header.html(nav);
  }

  // add footer to each page of front end
  static addFooter() {
    const container = $('#container');
    const footer = `
      <footer>
        Copyright &copy; 2017 曦文大佬团 All Rights Reserved
      </footer>
    `;

    container.after(footer);
  }
}

/*
 * a class including utility functions
 *
 *  Examples:
 *
 *    <form id="form">
 *      <input type='file' id="image">
 *      <img id="box" src="#" alt="your image">
 *    </form>
 *
 *    $('#image').change(function() {
 *      try {
 *        Utility.previewImage(this, $('#box'));
 *      } catch(err) {
 *        layer.msg(err.message, { icon: 5 });
 *      }
 *    });
 *
 *    Assume the URL of current page is: https://www.sogou.com/web?query=webpack&id=8899&name=yang
 *    const id = Utility.getQueryString('id'); // 8899
 * 
 * 
 *    Upload files example:
 * 
 *    const options = {
 *			form: $('form'),
 *			url: 'upload.php',
 *			data: {
 *				username: 'Sam Yang'
 *			},
 *			beforeSubmit: function() {
 *			  // something you want to do before submit
 *			},
 *			complete: function(res) {
 *				if(res.status === 'success') {
 *					// something to do after success
 *				}
 *			}
 *		};
 *		Utility.progress(options);
 */
class Utility {
  // show an image preview when you select an image
  // @param {[HTMLInputElement object]} image - an image file input element from <input type="file">
  // @param {[HTMLImageElement object]} previewBox - an <img> element to place image
  // @throws will throw an error if the file inputed is not an image file
  static previewImage(image, previewBox) {
    const img = image.files[0];
    const filter = /^(?:image\/bmp|image\/cis-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x-cmu-raster|image\/x-cmx|image\/x-icon|image\/x-portable-anymap|image\/x-portable-bitmap|image\/x-portable-graymap|image\/x-portable-pixmap|image\/x-rgb|image\/x-xbitmap|image\/x-xpixmap|image\/x-xwindowdump)$/i;
    
    if(!img) throw new Error('未选择图片！');
    
    if(!filter.test(img.type)) throw new Error('请选择有效的图片文件！');

    // 上传图片不得大于 300kB
    if(img.size > 307200) throw new Error('上传图片过大，请上传 300 kB 以下的图片！');

    const reader = new FileReader();

    // this event is triggered each time the reading operation is successfully completed
    reader.onload = (event) => {
      if(previewBox.src) {
        previewBox.src = event.target.result;
      } else {
        previewBox.attr('src', event.target.result);
      }
    };

    reader.readAsDataURL(img);
  }

  // get the value of the current URL query name
  // @param {string} name - the name of the value you want to get
  // @return value of the current URL query name
  static getQueryString(name) {
    const reg = new RegExp(`(?:^|&)${name}=(.*?)(?:&|$)`, 'i');
    let value = window.location.search.substr(1).match(reg);

    if(value !== null) value = window.decodeURIComponent(value[1]);

    return value;
  }

  // display progress bar when upload files through `canvas` instead of `css3`
  // @param {object} options - a object containing params
  //        => {object} form - a wrapped jq form element
  //        => {string} url - the upload url
  //        => {object} data - an object containing extra data that should be submitted along with the form
  //        => {function} beforeSubmit - callback function to be invoked before the form is submitted
  //        => {function} complete - callback function to be invoked after the uploading is completed
  static progress(options) {
    // load script asynchronously
    $.getScript('https://cdn.bootcss.com/jquery.form/4.2.2/jquery.form.min.js', function() {
      const shade = '<div class="progress-shade"></div>';
      const progressBar = '<div class="progress"><canvas id="progress" width="350" height="350"></canvas></div>';
      let canvasEnd;

      $(document.body).append(shade, progressBar);

      const canvas = $('#progress')[0];
      const ctx = canvas.getContext('2d');

      const uploadOptions = {
        url: options.url,
        type: 'POST',
        data: options.data,
        beforeSubmit: options.beforeSubmit || function() {},
        uploadProgress(event, position, total, percentComplete) {
          ctx.clearRect(0, 0, canvas.width, canvas.height); // clear the whole canvas before

          /* start to draw progress bar :) */
          ctx.beginPath();
          ctx.strokeStyle = "#00B5A9";
          ctx.lineWidth = 50;
          ctx.font = "56px serif";
          ctx.fillStyle = "#fff";
          ctx.textAlign = "center";
          ctx.textBaseline = 'middle';
          canvasEnd = -0.5 + 2 * percentComplete * 0.01;
          ctx.arc(175, 175, 144, -0.5 * Math.PI, canvasEnd * Math.PI, false); // change the length of progress bar
          ctx.fillText(`${percentComplete}%`, 175, 175); // change the number of progress bar
          ctx.stroke();
          /* finish drawing progress bar :) */

          if(percentComplete === 100) {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.beginPath();
            ctx.strokeStyle = "#00B5A9";
            ctx.lineWidth = 50;
            ctx.font = "22px serif";
            ctx.fillStyle = "#fff";
            ctx.textAlign = "center";
            ctx.textBaseline = 'middle';
            ctx.arc(175, 175, 144, -0.5 * Math.PI, 2 * Math.PI, false);
            ctx.fillText('正在完成其他操作', 175, 165);
            ctx.fillText('请耐心等候哦...', 175, 205);
            ctx.stroke();
          }
        },
        complete(xhr) {
          $('div').remove('.progress-shade');
          $('div').remove('.progress');

          options.complete(JSON.parse(xhr.responseText));
        }
      };

      options.form.ajaxSubmit(uploadOptions);
    });
  }
}

module.exports = {
  PUBLIC,
  RequestData,
  Storage,
  Render,
  Utility,
};
