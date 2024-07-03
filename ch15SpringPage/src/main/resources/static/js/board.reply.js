$(function(){
	let rowCount = 10;
	let currentPage;
	let count;
	
	/*===================
		댓글 목록
	===================*/
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;
		
		//서버와 통신
		$.ajax({
			url:'listReply',
			type:'get',
			data:{board_num:$('#board_num').val(),pageNum:pageNum,rowCount:rowCount},
			dataType:'json',
			beforeSend:function(){
				$('#loading').show();//로딩 이미지 표시
			},
			complete:function(){
				$('#loading').hide();//로딩 이미지 숨김
			},
			success:function(param){
				count = param.count;
				if(pageNum == 1){
					$('#output').empty();
				}
				//댓글수 읽어 오기
				displayReplyCount(param);
				//댓글 목록 작업
				$(param.list).each(function(index,item){
					let output = '<div class="item">';
					output += '<ul class="detail-info">';
					output += '<li>';
					output += '<img src="../member/viewProfile?mem_num='+item.mem_num+'" width="40" height="40" class="my-photo">';
					output += '</li>';
					output += '<li>';
					if(item.nick_name){
						output += item.nick_name+'<br>';
					}else{
						output += item.id+'<br>';
					}
					if(item.re_mdate){
						output += '<span class="modify-date">최근 수정일 : '+item.re_mdate+'</span>';
					}else{
						output += '<span class="modify-date">등록일 : '+item.re_date+'</span>';
					}
					output += '</li>';
					output += '</ul>';
					output += '<div class="sub-item">';
					output += '<p>'+item.re_content.replace(/\r\n/g,'<br>')+'</p>';
					//좋아요 시작
					
					//좋아요 끝
					if(param.user_num==item.mem_num){
						//로그인한 회원번호와 댓글 작성자 회원번호가 일치
						output += ' <input type="button" data-num="'+item.re_num+'" value="수정" class="modify-btn">';
						output += ' <input type="button" data-num="'+item.re_num+'" value="삭제" class="delete-btn">';
					}
					//답글 시작
					
					//답글 끝
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 output 추가
					$('#output').append(output);
				});//end of each
				//페이지 버튼 처리
				if(currentPage>=Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지 존재
					$('.paging-button').show();
				}
			},
			error:function(){
				alert('네트워크 오류 발생!!');
			}
		});
	}
	
	/*===================
		댓글 등록
	===================*/
	//댓글 등록
	$('#re_form').submit(function(event){
		if($('#re_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#re_content').val('').focus();
			return false;
		}
		let form_data = $(this).serialize();
		console.log(form_data);
		//서버와 통신
		$.ajax({
			url:'writeReply',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 해야 작성 가능합니다.');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//댓글 작성시 성공하면 새로 삽입한 글을 포함해서 첫번째 페이지의 게시글들을 다시 호출
					selectList(1);
					
				}else{
					alert('댓글 등록 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	/*===================
		댓글 수정
	===================*/
	
	/*======================
		댓글(답글) 등록, 수정 공통
	========================*/
	//textarea 에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구하기
		let inputLength = $(this).val().length;
		if(inputLength > 300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하인 경우
			//남은 글자수 구하기
			let remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id')=='re_content'){
				//댓글 등록 폼
				$('#re_first .letter-count').text(remain);
			}else if($(this).attr('id')=='mre_content'){
				//댓글 수정 폼 글자수
				$('#mre_first .letter-count').text(remain);
			}else if($(this).attr('id')=='resp_content'){
				//답글 등록 폼 글자수
				$('#resp_first .letter-count').text(remain);
			}else{
				//답글 수정 폼 글자수
				$('#mresp_first .letter-count').text(remain);
			}
		}
	});
	
	/*===================
		댓글 삭제
	===================*/
	
	/*===================
		댓글수 표시
	===================*/
	function displayReplyCount(param){
		
	}
	
	/*===================
		댓글 좋아요 목록
	===================*/
	
	/*===================
		댓글 좋아요 표시
	===================*/
	
	/*===================
		답글 등록
	===================*/
	
	/*===================
		답글 목록
	===================*/
	
	/*===================
		답글 수정
	===================*/
	
	/*===================
		답글 삭제
	===================*/
	
	/*===================
		초기 데이터 호출
	===================*/
	selectList(1);
	
});