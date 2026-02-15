'use strict';

jQuery(function ($) {

    // =========================
    // CSRFトークン取得
    // =========================
    const token = $('meta[name="_csrf"]').attr('content');
    const header = $('meta[name="_csrf_header"]').attr('content');

    // 全Ajax通信にCSRFを自動付与
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });

    // =========================
    // イベント登録
    // =========================
    $('#btn-update').on('click', () => updateUser());
    $('#btn-delete').on('click', () => deleteUser());
});


/**
 * 共通Ajax処理
 */
function sendAjax(method, url, successMessage, errorMessage) {

    const formData = $('#user-detail-form').serialize();

    $.ajax({
        type: method,
        cache: false,
        url: url,
        data: formData,
        dataType: 'json'
    })
    .done(() => {
        alert(successMessage);
        window.location.href = '/user/list';
    })
    .fail((jqXHR) => {
        console.error('Error:', jqXHR.status, jqXHR.responseText);
        alert(errorMessage + ' (status: ' + jqXHR.status + ')');
    });
}


/**
 * ユーザー更新処理
 */
function updateUser() {
    sendAjax(
        'PUT',
        '/user/update',
        'ユーザーを更新しました',
        'ユーザー更新に失敗しました'
    );
}


/**
 * ユーザー削除処理
 */
function deleteUser() {
    sendAjax(
        'DELETE',
        '/user/delete',
        'ユーザーを削除しました',
        'ユーザー削除に失敗しました'
    );
}