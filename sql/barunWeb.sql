CREATE table TB_USER (
	USER_ID				VARCHAR(100)		not null	            		
	, USER_PASSWORD		VARCHAR(100)		not null 	            
	, REGIST_DATE 		TIMESTAMP		not null default now()	
	, DELETE_YN			CHAR(1)			default 'N'				
	, PRIMARY key(USER_ID)
);
