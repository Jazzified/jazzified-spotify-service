function fn() {

    let env = karate.env
    env = env.toLowerCase().trim();

    if(!env)
        env = 'lcl';

    karate.log('Karate.env system property was: ', env)


    let config = {
        profile: env,
        host : 'http://localhost:8090',
        rootPath: '',
        baseUrl: ''

    };


    let profile

    return config;
}