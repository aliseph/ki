/*! nice Validator 0.5.0
 * (c) 2012-2013 Jony Zhang <zj86@live.cn>, MIT Licensed
 * http://niceue.com/validator/
 */
!function(e,t){"use strict";function i(s,a){var l,u,o=this;return!o instanceof i?new i(s,a):(E(a)&&(a={valid:a}),a=a||{},a.valid&&(o.isAjaxSubmit=!0),u=H(s,"data-"+f+"-option"),u=u&&"{"===u.charAt(0)?Function("return "+u)():{},l=W[a.theme||u.theme||P.theme],o.options=e.extend({},P,l,u,a),o.$el=e(s),o.rules=new r(o.options.rules,!0),o.messages=new n(o.options.messages,!0),o.elements={},o.fields={},o.deferred={},o.errors={},o.isValid=!0,o._init(),t)}function r(e,t){var i=t?t===!0?this:t:r.prototype;if(D(e))for(var n in e)i[n]=s(e[n])}function n(e,t){var i=t?t===!0?this:t:n.prototype;if(D(e))for(var r in e){if(!e[r])return;i[r]=e[r]}}function s(t){switch(e.type(t)){case"function":return t;case"array":return function(e){return t[0].test(e.value)||t[1]||!1};case"regexp":return function(e){return t.test(e.value)}}}function a(t){var i="";return e.map(t.split(" "),function(e){i+=","+("#"===e.charAt(0)?e:'[name="'+e+'"]')}),i.substring(1)}function l(t){if(t&&t.tagName){var i=t;switch(t.tagName){case"INPUT":case"SELECT":case"TEXTAREA":i=t.form||e(t).closest(".n-"+f);break;default:i=e(t).closest(".n-"+f)}return e(i).data(f)||e(i)[f]().data(f)}}function u(i,r){var n=e.trim(H(i,_+"-"+r));if(n)return n=Function("return "+n)(),n?s(n):t}function o(e,t,i){var r=t.msg;return D(r)&&i&&(r=r[i]),N(r)||(r=H(e,"data-msg-"+i)||H(e,"data-msg")||""),r}function d(e){var t;return e&&(t=S.exec(e)),t?t[1]:""}function c(e){return"INPUT"===e.tagName&&"checkbox"===e.type||"radio"===e.type}var f="validator",g="n-ok",p="n-error",m="n-tip",h="n-loading",v="n-valid",y="n-invalid",b="msg-box",k="aria-invalid",_="data-rule",w="data-target",M="data-tip",x="data-inputstatus",O="novalidate",$=":verifiable",A=/(\w+)(?:\[(.*)\]$|\((.*)\)$)?/,R=/(?:([^:;\(\[]*):)?(.*)/,V=/[^\x00-\xff]/g,S=/^.*(top|right|bottom|left).*$/,j=/(?:(post|get):)?(.+)/i,C=/<|>/g,F=e.noop,T=e.proxy,E=e.isFunction,q=e.isArray,N=function(e){return"string"==typeof e},D=function(e){return e&&"[object Object]"===Object.prototype.toString.call(e)},I=!window.XMLHttpRequest,H=function(e,i,r){return r===t?e.getAttribute(i):(null===r?e.removeAttribute(i):e.setAttribute(i,""+r),t)},L=window.console||{log:F,info:F,warn:F},P={debug:0,timely:1,theme:"default",stopOnError:!1,ignore:"",beforeSubmit:F,valid:F,invalid:F,msgWrapper:"span",msgMaker:function(e){var t,i={error:p,ok:g,tip:m,loading:h}[e.type];return t='<span class="msg-wrap '+i+'" role="alert">',t+=(e.arrow||"")+(e.icon||"")+'<span class="n-msg">'+e.msg+"</span>",t+="</span>"},msgIcon:'<span class="n-icon"></span>',msgArrow:"",msgClass:"",defaultMsg:"{0} is not valid.",loadingMsg:"Validating..."},W={"default":{formClass:"n-default",msgClass:"n-right",showOk:""}};e.fn[f]=function(t){var r=this,n=arguments;return r.is(":input")?r:(!r.is("form")&&(r=this.find("form")),!r.length&&(r=this),r.each(function(){if(N(t)){if("_"===t.charAt(0))return;var r=e(this).data(f);r&&r[t].apply(r,Array.prototype.slice.call(n,1))}else new i(this,t)}),this)},e.fn.isValid=function(e,i){var r,n,s=l(this[0]);return s?(i===t&&(i=!0),s.checkOnly=i,r=this.is(":input")?this:this.find($),n=s._multiValidate(r,function(t){E(e)&&e.call(null,t),s.checkOnly=!1},!0),E(e)?this:n):!0},e.expr[":"].verifiable=function(e){var t=e.nodeName.toLowerCase();return("input"===t&&"submit"!==e.type&&"button"!==e.type&&"reset"!==e.type||"select"===t||"textarea"===t)&&e.disabled===!1&&!H(e,O)},i.prototype={_init:function(){var t=this,i=t.options,r=t.fields;q(i.groups)&&e.map(i.groups,function(i){if(!N(i.fields)||!E(i.callback))return null;var n=t.$el.find(a(i.fields)),s=function(){return i.callback.call(t,n)};e.extend(s,i),e.map(i.fields.split(" "),function(e){r[e]=r[e]||{},r[e].group=s})}),D(i.fields)&&e.each(i.fields,function(e,t){t&&(r[e]=N(t)?{rule:t}:t)}),t.$el.find($).each(function(){t._parse(this)}),t.msgOpt={type:"error",pos:d(i.msgClass),cls:i.msgClass,style:i.msgStyle,icon:i.msgIcon,arrow:i.msgArrow,show:i.msgShow,hide:i.msgHide},t.$el.data(f)||(t.$el.on("submit."+f+" validate."+f,T(t,"_submit")).on("reset."+f,T(t,"_reset")).on("validated.field."+f,$,T(t,"_validatedField")).on("validated.rule."+f,$,T(t,"_validatedRule")).on("focusin."+f+" click."+f+" showtip."+f,$,T(t,"_focus")).on("focusout."+f+" validate."+f,$,T(t,"_blur")).on("click."+f,":radio,:checkbox",T(t,"_click")),i.timely>=2&&t.$el.on("keyup."+f+" paste."+f,$,T(t,"_blur")).on("change."+f,"select",T(t,"_click")),t.$el.data(f,t).addClass("n-"+f+" "+i.formClass),H(t.$el[0],O,!0))},_multiValidate:function(i,r,n){var s=this,a=s.options;return a.ignore&&(i=i.not(a.ignore)),s.isValid=!0,s.deferred={},i.each(function(e,i){var r=s.getField(i);if(r)return s._validate(i,r,n),!s.isValid&&a.stopOnError?!1:t}),e.when.apply(null,e.map(s.deferred,function(e){return e})).done(function(){r.call(s,s.isValid)}),e.isEmptyObject(s.deferred)?s.isValid:t},_submit:function(i,r){var n=this,s=n.options,a=i.target;if(n.submiting&&"only"!==r)return E(n.submiting)&&n.submiting.call(n),i.preventDefault();if("only"!==r&&("validate"!==i.type||"FORM"===a.tagName)){if(s.beforeSubmit.call(n,a)===!1)return i.preventDefault();if(n.isAjaxSubmit===t)if(null===H(a,"action"))n.isAjaxSubmit=!0;else{var l=e[e._data?"_data":"data"](n.$el[0],"events");n.isAjaxSubmit=l&&l.valid&&e.map(l.valid,function(e){return-1!==e.namespace.indexOf("form")?1:null}).length?!0:!1}return n._reset(),n.submiting=!0,n._multiValidate(n.$el.find($),function(t){var i,r="focus.field",l=t||2===s.debug?"valid":"invalid";if(t)n.isAjaxSubmit||e(a).trigger("submit",["only"]);else{var u=n.$el.find(":input."+y+":first");u.trigger(r),I&&u.trigger(r),i=e.map(n.errors,function(e){return e})}s[l].call(n,a,i),n.$el.trigger(l+".form",[a,i]),n.submiting=!1},!0),(n.isAjaxSubmit||!e.isEmptyObject(n.deferred))&&i.preventDefault(),n.isValid}},_reset:function(){var t=this;e.each(t.elements,function(i,r){H(r,x,null),H(r,k,null),e(r).removeClass(v+" "+y),t.hideMsg(r)}),t.errors={},t.isValid=!0},_focus:function(e){var t=e.target;this.submiting||""!==t.value&&("false"===H(t,k)||"tip"===H(t,x))||this.showMsg(t,{msg:H(t,M),type:"tip"})},_blur:function(t,i){var r,n,s=this,a=s.options,l=t.target,u=t.type,o=100;if(!i&&"paste"!==u){if("validate"===u)n=!0,o=0;else{if(H(l,"notimely"))return;if(a.timely>=2&&"keyup"!==u)return}if(a.ignore&&e(l).is(a.ignore))return;if("keyup"===u){var d=t.keyCode,c={8:1,9:1,16:1,32:1,46:1};if(9===d&&!l.value)return;if(48>d&&!c[d])return;o=a.timely>=100?a.timely:500}}r=s.getField(l),r&&(o?(r.timeout&&clearTimeout(r.timeout),r.timeout=setTimeout(function(){s._validate(l,r,n)},o)):s._validate(l,r,n))},_click:function(e){this._blur(e,!0)},_parse:function(e){var i,r=this,n=e.name;return(e.id&&"#"+e.id in r.fields||!e.name)&&(n="#"+e.id),n?(i=r.fields[n]||{},i.rule||(i.rule=H(e,_)||""),H(e,_,null),i.rule&&(i.key=n,i.required=-1!==i.rule.indexOf("required"),i.must=i.must||!!i.rule.match(/match|checked/),i.required&&H(e,"aria-required",!0),("timely"in i&&!i.timely||!r.options.timely)&&H(e,"notimely",!0),N(i.target)&&H(e,w,i.target),N(i.tip)&&H(e,M,i.tip),r.fields[n]=r._parseRule(i)),t):H(e,_,null)},_parseRule:function(i){var r,n=R.exec(i.rule);if(n)return i.display=n[1],i.rules=[],r=(n[2]||"").split(";"),e.map(r,function(r){var n=A.exec(r);return n?(n[3]&&(n[2]=n[3]),i.rules.push({method:n[1],params:n[2]?e.trim(n[2]).split(", "):t}),t):null}),i.vid=0,i.rid=i.rules[0].method,i},_validatedField:function(t,i,r){var n=this,s=n.options,a=t.target,l=i.isValid=!!r.valid;l?r.type="ok":n.submiting&&(n.errors[i.key]=r.msg),e(a).attr(k,!l).addClass(l?v:y).removeClass(l?y:v).trigger((l?"valid":"invalid")+".field",[i,r]),i.old.ret=r,n.elements[i.key]=a,(i.msgMaker||s.msgMaker&&!n.checkOnly)&&(!r.showOk&&r.msg||r.showOk&&s.showOk!==!1?n.showMsg(a,r,i):n.hideMsg(a,r))},_validatedRule:function(i,r,n,s){r=r||a.getField(u);var a=this,l=a.options,u=i.target,d="",c=r.rid,f=!1,g=!1;if(s=s||{},n===!0||n===t?f=!0:(d=o(u,r,c),d||(N(n)?(d=n,n={error:d}):D(n)&&(n.error?d=n.error:(f=!0,n.ok&&N(n.ok)&&(g=!0),d=n.ok))),s.msg=(f?d:d||a.messages[c]||P.defaultMsg).replace("{0}",r.display||"")),f){if(s.valid=!0,!g){var p=r.ok||H(u,"data-ok");p?(g=!0,s.msg=p):N(l.showOk)&&(g=!0,s.msg=l.showOk)}s.showOk=g,e(u).trigger("valid.rule",[c,s.msg])}else a.isValid=!1,e(u).trigger("invalid.rule",[c,s.msg]);l.debug&&L[f?"info":"warn"](r.vid+": "+c+" -> "+(s.msg||!0)),f&&r.old.value!==t&&r.old.value!==u.value?(r.vid=0,a._checkRule(u,r)):f&&r.vid<r.rules.length-1?(r.vid++,a._checkRule(u,r)):(r.vid=0,e(u).trigger("validated.field",[r,s]))},_checkRule:function(i,r){var n,s=this,a=r.key,l=r.rules[r.vid],o=l.method,d=l.params;if(!s.submiting||!s.deferred[a])if(r.rid=o,r.old.value=i.value,n=(u(i,o)||s.rules[o]||function(){return!0}).call(s,i,d,r),D(n)&&E(n.then)){var c=function(e){return N(e)||D(e)&&("error"in e||"ok"in e)?e:t};s.deferred[a]=n,!s.checkOnly&&s.showMsg(i,{type:"loading",msg:s.options.loadingMsg},r),n.then(function(n,s,a){var l,u=a.responseText;""===u?u=!0:"{"===u.charAt(0)&&(u=e.parseJSON(u)||{},l=c(u),l===t&&(l=c(u.data)),u=l||!0),e(i).trigger("validated.rule",[r,u])},function(t,n){e(i).trigger("validated.rule",[r,n])}),r.isValid=t}else e(i).trigger("validated.rule",[r,n])},_validate:function(i,r,n){if(!i.disabled&&!H(i,O)){r.rules||this._parse(i);var s,a,l=this,u=l.options,o=e(i),d={},f=r.group,g="tip"===H(i,x),p=r.isValid=!0;if(s=r.old=r.old||{},n=n||r.must,f&&(e.extend(d,f),a=f.call(l),a!==!0?(N(a)&&(a={error:a}),r.vid=0,r.rid="group",p=!1):(a=t,l.hideMsg(i,d))),p&&!r.required&&""===i.value){if(g)return;if(l._focus({target:i}),s.value="",!c(i))return o.trigger("validated.field",[r,{valid:!0}]),t}else if(!n&&s&&s.ret!==t&&s.value===i.value){if(s.ret.valid||(l.isValid=!1),g)return;if(""!==i.value)return d=s.ret,o.trigger("validated.field",[r,d]),t}u.debug&&L.log(r.key),a!==t?o.trigger("validated.rule",[r,a,d]):r.rule&&l._checkRule(i,r)}},_getMsgOpt:function(t){return e.extend({},this.msgOpt,N(t)?{msg:t}:t)},getField:function(e){var t,i=this;return t=e.id&&"#"+e.id in i.fields||!e.name?"#"+e.id:e.name,H(e,_)&&i._parse(e),i.fields[t]},test:function(i,r){var n,s,a,l=this,u=A.exec(r);return u?(u[3]&&(u[2]=u[3]),s=u[1],a=u[2]?e.trim(u[2]).split(", "):t,s in l.rules&&(n=l.rules[s].call(l,i,a)),n===!0||n===t||n):!0},getRangeMsg:function(e,t,i,r){if(t){var n=this,s=n.messages[i]||"",a=t[0].split("~"),l=a[0],u=a[1],o="rg",d=[""],c=+e===+e;if(2===a.length){if(l&&u){if(c&&e>=+l&&+u>=e)return!0;d=d.concat(a)}else if(l&&!u){if(c&&e>=+l)return!0;d.push(l),o="gt"}else if(!l&&u){if(c&&+u>=e)return!0;d.push(u),o="lt"}}else{if(e===+l)return!0;d.push(l),o="eq"}return s&&(r&&s[o+r]&&(o+=r),d[0]=s[o]),n.renderMsg.apply(null,d)}},renderMsg:function(){var e=arguments,t=e[0],i=e.length;if(t){for(;--i;)t=t.replace("{"+i+"}",e[i]);return t}},_getMsgDOM:function(t,i){var r,n,s,a=e(t);if(a.is(":input")?(s=i.target||H(t,w),s&&(s=this.$el.find(s),s.length&&(s.is(":input")?t=s.get(0):r=s)),r||(n=!c(t)&&t.id?t.id:t.name,r=this.$el.find(this.options.msgWrapper+"."+b+'[for="'+n+'"]'))):r=a,!r.length)if(a=this.$el.find(s||t),r=e("<"+this.options.msgWrapper+">").attr({"class":b+(i.cls?" "+i.cls:""),style:i.style||"","for":n}),c(t)){var l=a.parent();r.appendTo(l.is("label")?l.parent():l)}else r[i.pos&&"right"!==i.pos?"insertBefore":"insertAfter"](a);return r},showMsg:function(t,i,r){if(i=this._getMsgOpt(i),i.msg||i.showOk){t=e(t).get(0),H(t,x,i.type);var n=this._getMsgDOM(t,i),s=n[0].className;!S.test(s)&&n.addClass(i.cls),I&&"bottom"===i.pos&&(n[0].style.marginTop=e(t).outerHeight()+"px"),n.html(((r||this.getField(t)).msgMaker||this.options.msgMaker).call(this,i)),n[0].style.display="",E(i.show)&&i.show.call(this,n,i.type)}},hideMsg:function(t,i){t=e(t).get(0),i=this._getMsgOpt(i);var r=this._getMsgDOM(t,i);r.length&&(E(i.hide)?i.hide.call(this,r,i.type):r[0].style.display="none")},mapMsg:function(t){var i=this;e.each(t,function(e,t){var r=i.elements[e]||i.$el.find(':input[name="'+e+'"]')[0];i.showMsg(r,t)})},setMsg:function(e){new n(e,this.messages)},setRule:function(t){new r(t,this.rules),e.map(this.fields,function(e){e.old={}})},setField:function(i,r){var n=this,s={};if(N(i)){if(null===r)return e.map(i.split(" "),function(e){e&&n.fields[e]&&(n.fields[e]=null)}),t;r&&(s[i]=r)}else D(i)&&(s=i);n.options.fields?e.extend(n.options.fields,s):n.options.fields=s,n._init()},holdSubmit:function(e){e===t&&(e=!0),this.submiting=e},destroy:function(){this._reset(),this.$el.off("."+f).removeData(f)}},e(function(){e("body").on("focusin",":input["+_+"]",function(){var t=this,i=l(t);i?(H(t,_)&&i._parse(t),e(t).trigger("focusin")):H(t,_,null)}).on("click submit","form",function(t){if(!H(this,"novalidate")){var i,r=e(this);r.data(f)||(i=r[f]().data(f),e.isEmptyObject(i.fields)?(H(this,O,!0),r.removeData(f)):"submit"===t.type&&i._submit(t))}})}),new r({required:function(t){return!!e.trim(t.value)},integer:function(e,t){var i,r="0|",n="[1-9]\\d*",s=t?t[0]:"*";switch(s){case"+":i=n;break;case"-":i="-"+n;break;case"+0":i=r+n;break;case"-0":i=r+"-"+n;break;default:i=r+"-?"+n}return i="^(?:"+i+")$",RegExp(i).test(e.value)||this.messages.integer[s]},match:function(t,i,r){var n,s,a,l,u,o,d,c="eq";if(i&&(1===i.length?a=i[0]:(c=i[0],a=i[1]),u="#"===a.charAt(0)?a:':input[name="'+a+'"]',o=this.$el.find(u)[0]))switch(d=this.getField(o),r.init_match||(this.$el.on("valid.field."+f,u,function(){!r.isValid&&t.value&&e(t).trigger("validate")}),r.init_match=1),l=this.messages.match[c].replace("{1}",d.display||a),n=t.value,s=o.value,c){case"lt":return+s>+n||l;case"lte":return+s>=+n||l;case"gte":return+n>=+s||l;case"gt":return+n>+s||l;default:return n===s||l}},range:function(e,t){return this.getRangeMsg(+e.value,t,"range")},checked:function(t,i){if(!c(t))return!0;var r=this.$el.find('input[name="'+t.name+'"]').filter(function(){return!this.disabled&&this.checked&&e(this).is(":visible")}).length;return i?this.getRangeMsg(r,i,"checked"):!!r||this.messages.required},length:function(e,t){var i=e.value,r=(t[1]?i.replace(V,"xx"):i).length;return t&&"~"===t[0].charAt(0)&&(t[0]="0"+t[0]),this.getRangeMsg(r,t,"length",t[1]?"_2":"")},remote:function(t,i){var r,n=this,s={};return i?(r=j.exec(i[0]),s[t.name]=t.value,i[1]&&e.map(i.slice(1),function(e){s[e]=n.$el.find(':input[name="'+e+'"]').val()}),e.ajax({url:r[2],async:!0,type:r[1]||"POST",data:s,cache:!1})):!0},filter:function(e,t){var i=t?RegExp("["+t[0]+"]","g"):C;return e.value=e.value.replace(i,""),!0}}),e[f]={config:function(t){e.each(t,function(e,t){"rules"===e?new r(t):"messages"===e?new n(t):P[e]=t})},setTheme:function(t,i){D(t)?e.each(t,function(e,t){W[e]=t}):N(t)&&D(i)&&(W[t]=i)}}}(jQuery);