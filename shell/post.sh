获取ACCESS_TOKEN
wget -q -t 1 -O- 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2b44d79da6a46efe&secret=8c42dc03a29139f765de94feb75ae787'
设置菜单
wget -q -t 1 -O- --post-data "`cat menu.json`" https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
设置所属行业（模板消息）
wget -q -t 1 -O- --post-data "`cat industry.json`" https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN
获取所属行业（模板消息）
wget -q -t 1 -O- https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN
获得模板ID（模板消息）
wget -q -t 1 -O- https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
获取模板列表（模板消息）
wget -q -t 1 -O- https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN
