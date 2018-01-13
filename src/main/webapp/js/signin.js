/**
 * Created by Sam Yang
 */

'use strict';

import '../css/signin.css';
import '../public/js/gt.js';
import { PUBLIC, RequestData, Storage } from './public.js';

class SignIn {
  // 极验验证码：http://www.geetest.com/
  // 文档：http://docs.geetest.com/install/client/web-front/
  // 配置demo：http://www.geetest.com/demo/fullpage.html
  static captcha() {
    const params = {
      url: `${PUBLIC.requestUrl}/user/startCaptcha?t=${(new Date()).getTime()}`, // forbid caching
      method: 'GET',
      data: {}
    };

    RequestData.getData(params.url, params.method, params.data, function(res, status, messages) {
      if(status === 'success') {
        const data = res;

        initGeetest({
          gt: data.gt,
          challenge: data.challenge,
          new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
          offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
          product: "float", // 产品形式，包括：float，popup
          width: "314px"
        }, SignIn.handler.bind(Object.create(null), data.sessionid));
      }
    });
  }

  // 极验验证码回调
  static handler() {
    const sessionid = arguments[0]; // {string} 验证同一类请求
    const captchaObj = arguments[1]; // {object} 验证码对象，用于调用极验的相应接口

    captchaObj.appendTo('#captcha');

    captchaObj.onReady(function() {
        $("#wait").hide();
    });

    $('#submit').click(function() {
        const result = captchaObj.getValidate();
        const account = $('#account').val().trim();
        const password = $('#password').val().trim();
        const type = $('#type').val().trim();

        if(!account) {
          layer.msg('用户名不能为空！', { icon: 0 });

          return false;
        } else if(!password) {
          layer.msg('密码不能为空！', { icon: 0 });

          return false;
        } else if(!result) {
          layer.msg('请完成验证', { icon: 0 });

          return false;
        }
        
        const params = {
          url: `${PUBLIC.requestUrl}/user/verifyLogin`,
          method: 'POST',
          data: {
            account,
            password,
            type,
            sessionid: sessionid,
            challenge: result.geetest_challenge,
            validate: result.geetest_validate,
            seccode: result.geetest_seccode
          }
        };

        RequestData.getData(params.url, params.method, params.data, function(res, status, messages) {
          if (res.status === 'success') {
            Storage.set('type', res.obj.type);
            Storage.set('login', res.obj.sessionid ? 'true' : 'false');
            Storage.set('avatar', res.obj.avatar);
            Storage.set('name', res.obj.name);
            Storage.set('sessionid', res.obj.sessionid);

            location.href = './index.html';
          } else if(res.status === 'fail') {
            layer.msg(res.msg, { icon: 5 });

            captchaObj.reset();
          }
        });

        return false;
    });
  }
}

$(function() {
  SignIn.captcha();
});
