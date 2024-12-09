async function isLogin(){
    try{
        const response =await fetch(`/member/isLogin`,{
            method: "GET",
            credentials: "include",
        })
        const data = await response.json();
        if(data.login) return {login: true, userId: data.userId};
        return {login: false, userId: data.userId};

    }catch(error){
        console.log(error);
    }
}