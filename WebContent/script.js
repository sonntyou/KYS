jQuery(document).ready(function($) {


	// "品目の追加"ボタンを押した場合の処理
	$('#btn_add').click(function(){
		// 品目入力欄を追加
		var len_list = $('#mail_list > li').length;
		var new_list = '<li><input type="text" name="mail" required>@level-five.jp<input type="button" value="-"></li>';
		$('#mail_list').append(new_list);

		// 削除ボタンの一旦全消去し、配置し直す
		$('#mail_list input[type="button"]').remove();
		len_list++;
		for (var i = 0; i < len_list; i++) {
			var new_btn = '<input type="button" value="-">';
			$('#mail_list > li').eq(i).append(new_btn);
		}

	});

	// 削除ボタンを押した場合の処理
	$(document).on('click', '#mail_list input[type="button"]', function(ev) {
		// 品目入力欄を削除
		var idx = $(ev.target).parent().index();
		$('#mail_list > li').eq(idx).remove();

		var len_list = $('#mail_list > li').length;

		// 入力欄がひとつになるなら、削除ボタンは不要なので消去
		if (len_list == 1) $('#mail_list input[type="button"]').remove();

		// 入力欄の番号を振り直す
		for (var i=0; i<len_list; i++) {
			$('#mail_list > li').eq(i).children('input[type="text"]').attr('name', prefix_order_list + i);
		}
	});
});

