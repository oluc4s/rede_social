# rede_social
uma breve introdução a uma rede social em um aplicativo.
                     

-- Usei o Shared Preferences para criar um tipo de sessão
SharedPreferences sharedPreferences =  getApplicationContext().getSharedPreferences("NOME-SESSÃO", Context.MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPreferences.edit();

//salvando em um tipo de sessão
editor.putString("user", userString);
editor.apply();


-- Butter Knife
    implementation 'com.jakewharton:butterknife:8.8.1'
    implementation 'com.android.support:support-v4:28.0.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
-- retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
-- gson
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    
    
    https://play.google.com/store/apps/details?id=com.s2start.matheus.fiveconn
    
     LOGIN : a
     SENHA: a

