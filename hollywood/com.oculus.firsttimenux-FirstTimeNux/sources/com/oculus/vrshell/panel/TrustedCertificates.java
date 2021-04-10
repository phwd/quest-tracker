package com.oculus.vrshell.panel;

import android.content.pm.Signature;

public class TrustedCertificates {
    public static final String FBANDROID_DEV_CERTIFICATE = "3082037b30820263a0030201020204232eae62300d06092a864886f70d0101050500306d310b30090603550406130255533110300e06035504081307556e6b6e6f776e3110300e06035504071307556e6b6e6f776e3110300e060355040a1307556e6b6e6f776e3110300e060355040b1307416e64726f6964311630140603550403130d416e64726f69642044656275673020170d3133313233313232333530345a180f32303532303433303232333530345a306d310b30090603550406130255533110300e06035504081307556e6b6e6f776e3110300e06035504071307556e6b6e6f776e3110300e060355040a1307556e6b6e6f776e3110300e060355040b1307416e64726f6964311630140603550403130d416e64726f696420446562756730820122300d06092a864886f70d01010105000382010f003082010a028201010092de6e2026c86cd5441e788c25f40d5bdcd177bad77a67c3027dcfe8ad163fd5155ea4536b58be1999369ec71667708185081d35cdecb97124c4eccacd76e1964286e08c058aad2fe7f834dad15884075a5bf5483787576bf55887fc9833fb3d219d2936f150b981b939ebad4fe7d9b561e2085711d38845492cd4ba9aab415fb21b82a475d805736987fb173ec0cb57f1e2e640d98f2de1dee9270015b0e863385ddbfbb69b87dc55014a8a2a355919d17d156305b6e6b1e7ee024a718c8907089262407fd443de47eff2da48d774792b20df93218043df995dcd42376980c62bfd2d23c34fb8be2e70ad968625780a0ee8a9454972d44db125f086dfe9d1610203010001a321301f301d0603551d0e041604140bf9fe3889d28a9c58f0c10ab70e4328d823f320300d06092a864886f70d010105050003820101005fd27615bfce63184d1250c1b91175f0bce2f9c44e7ae7777f8d85c9e1b2153d3d10066bb0019a2c0a6d7e3c02dbdff776d6b6ff272ebcf468259868a40c0e77d430455e7bcfc3b9ff2f4f7a8a931b75b052bbf4245f31e28e0f491018ab7e97d7fb8e1d73d312cd08dc96fb9dd3939ca2e1d6adf65cff4a58d9ec916aea06d43b5243fad59cf1146e3c71ab66d324f012935a314e96aff81baec183c83790f99a86dab56b5bc5b26d1694bc355e4290ec02edba115d57bbdd65a8b0e4693456b5ac2a3a07a4447e5d7ad4de9dfc8a99c53cd4ee6c2e65b9bfdec689ae8e2319d64cb83a6308a2187e30c03feef687718d4903e54003a9a0b57f78dbd9a8892a";
    public static final Signature FBANDROID_DEV_SIGNATURE = new Signature(FBANDROID_DEV_CERTIFICATE);
    public static final String OCULUS_APPS_BRANCH_DEV_CERTIFICATE = "30820301308201e9a00302010202045799972c300d06092a864886f70d01010b05003031310b30090603550406130255533110300e060355040a1307416e64726f69643110300e06035504031307416e64726f6964301e170d3136303330383032303235305a170d3433303732353032303235305a3031310b30090603550406130255533110300e060355040a1307416e64726f69643110300e06035504031307416e64726f696430820122300d06092a864886f70d01010105000382010f003082010a02820101009856da41463e79764f73f62b455d02cebaeb9d06c4c22108c746bae789365be0d8eb8f1db8df9fb9de30a5b8eee74d314f322f0b64294b0f3974e2cf010d8314f31c7eee86d519e071c7d82db0227c1d5fd04da835d510351b89b99d905e39c45bbda809e0ec9a86aa99a28168720d853da9af96e630b161b794402958e468bc63124d00d63454e4d08a2065f066766a5190b92a5b27dca2cf815adda0205dbbf3fb8135b66d794922b467d736a7aa47ef12c9202523d3a82c1a75e35bed246a92b676dc88908c8ac0d66d813f78531c129d9bd51cab9366a47365a85366e5708f657d620d735a7f4c5145baa7ee61e03c0f89ef5262374f00c8eb266d2bbcef0203010001a321301f301d0603551d0e04160414c91bf0566958520ae5a1c46186a549a1016423c1300d06092a864886f70d01010b05000382010100218d69bbb620e17eede388eda49727b7940ec740394cc272fa63ce83bff0b2e5a9fd65fedb7d4c38ce34930e5c8a7f80868eaf0eb0afbd1f9cfe4af8f51c0549b47eb724cd75e88080b84f1701387f00493faa28db30363444934ce731dcf226bca4c49b8dbc443ac00fd68edb2aee26596b3af87972481d486723168a5a4ce65680a78a2847a5dbb99ba4b9d3ee9ce59d5dc551b279dba67af18ca93d982f3869f18a72529fde5c7893955ab04b90a7531fb43a82c6d94d10fddb5ed01687e538649b2d303567bc518fa0f6ae710ab32c32a89427e69aae507865c99aafbcc324b7b579c941e5f1af494230491ff5877b4628e66d9471847796f92f0379af62";
    public static final Signature OCULUS_APPS_BRANCH_DEV_SIGNATURE = new Signature(OCULUS_APPS_BRANCH_DEV_CERTIFICATE);
    public static final String OCULUS_APPS_CERTIFICATE = "3082038d30820275a00302010202041303858a300d06092a864886f70d01010b05003077310b3009060355040613025553310b3009060355040813024341311330110603550407130a4d656e6c6f205061726b31173015060355040a130e4f63756c75732056522c204c4c4331173015060355040b130e4f63756c75732056522c204c4c43311430120603550403130b4f63756c75732041707073301e170d3134313033303231343032315a170d3432303331373231343032315a3077310b3009060355040613025553310b3009060355040813024341311330110603550407130a4d656e6c6f205061726b31173015060355040a130e4f63756c75732056522c204c4c4331173015060355040b130e4f63756c75732056522c204c4c43311430120603550403130b4f63756c7573204170707330820122300d06092a864886f70d01010105000382010f003082010a0282010100a34c7fbd96dea95d811f21a0d760cabca85f2df5dbe19b62aca7a3e3e0b6e48f7e173345f2753c458d639cc2e222eeee4cfb5e89ac1c5060044414056709a43a7f1a6f1e4f2c3d708c48912634be08ad6da1fff92d9c0d4b85e4bc8a8b1c27de84c04eb1e6577c10693500af46beaf7bd2ce72a06a46f4e565859f242cecbef1a431ae8096db712cebe73840bacc64de6b99077ba1ca8c20e5d0b4870bd161f087abbf568dfef58e604068ca6f7cdea183c8681c306bc30cb223e7c5ce3a2fcf00d13e158475b3700ef62dcca46c57baecca4d91052e20b861a1a085d5fc8e99aed8c4a9cfd550d01e54ef5062133befcf7f9ea6a5a1d1575a72cd82279f34030203010001a321301f301d0603551d0e04160414ec7ccdaf8ccdc6dd1b87d4b561582ad8ec397b80300d06092a864886f70d01010b0500038201010014c557ecddc17ba8b979618036e7093b8e7307f624644532c31239f4dc83fd9424267ca7670fa9f78689cbc5bfd8a6312fdc214bd159d614a8753ceec0f2b2d9166217895b13c992e1d10ca625a3153dacfa51dbdb7fde139bd59510f5add29f88be3826b445ce7386dcf06577be72fbdf118fa81179091fad275981409e02d40422d8e86df5827a2c496a4b905b4825c3f1ba0af4cf509bf9b0d4d608cf0b4320ac60d1a8cbcc384de24f11a4c93c0fc5b887cbd6855871af1b1ef729c51a8a123efc6a627b6494e81a264587443b9156ad17772863c2f9685fae9c72a552b333f8ef2822bce5d4d178e45b3a8dd4e321924f6a5ba43f732971a9e74cbf0433";
    public static final Signature OCULUS_APPS_SIGNATURE = new Signature(OCULUS_APPS_CERTIFICATE);
    public static final String OCULUS_BUNDLES_CERTIFICATE = "308205cf308203b7a003020102020478f8c9ac300d06092a864886f70d01010b0500308197310b3009060355040613025553311330110603550408130a43616c69666f726e6961311330110603550407130a4d656e6c6f205061726b31233021060355040a131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c4331233021060355040b131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c43311430120603550403130b4f63756c75732041707073301e170d3138313032323136343330305a170d3436303330393136343330305a308197310b3009060355040613025553311330110603550408130a43616c69666f726e6961311330110603550407130a4d656e6c6f205061726b31233021060355040a131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c4331233021060355040b131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c43311430120603550403130b4f63756c7573204170707330820222300d06092a864886f70d01010105000382020f003082020a028202010088a687325e9bb2f07403101211f8602bf9de00f662d8d0ed51f116a58f5b2a6b8d1cf1335b46f8e8ccbdb0f5f04bc55c9b020fa2e90c827ff07b75b4782f2e90b8e9a8374e88839b6c48f992e8ae59d27936407f20e54578ca2c5e370379cee24c31e042ee5d535266cbcfdce5cf483a92830d5a69c522615b8a172e96ae0d30a60867cd128e65bde747b18e570621c6dd4668e14c4fd7f89c6d9f8459d3b1ac821672e77dcfa9209747be61c097eb9e5ab7f4e00493f6c85783c832e59757a09ed707f0633f865230aed27ab2be10304f5b939bb151f03904ba45343f13a49332d58322a4a6dc1ebf0e053b6d4b2b9b6dc220ce350db4253213670cb29df45b58e748d2bb00f8477c121b3775a3679aab2d69a376fb1eb187fe3f3986552808a78573e43938d7b126b4c878e1b14e9f64743d92be9ff4f60d3a4782176b385b57f83b2982f73b4940ef4fb7a4b095ff8079aa6dcf9cb5904f20f18aa90e3eceb862f0b643b445f08353cff19f40b0f429b358aa2478615e6062d501a83d278cc905763e75a017bed19e413de591644d38c2c9814ec6a784179273038e3c3b400234be72496e26402678554c1d931dad528bcc119546c959d46c36ec900148574838957ba962223c15f15268e1fb292e7bf0179f0735d35f15535e96b98f17ab11269e3af7b4ce334c76b2be1ab3064d7c34c9266b35dcacbb747b758ded99470203010001a321301f301d0603551d0e0416041441da93c241a469f7b4220ac30d41d26ab4844ebc300d06092a864886f70d01010b050003820201003082681a192551c427f6f518579be49819d3848d340f8d409d030e55418018164021261dfe7475b3202ade89d8c45f47da8e3018a35b3bed1e4603c20939bfac2b77223dee744783555f905103a44f0eed297620b28f33cc382d579bc8343a0ee78ce8afcc2489727593d9eb9404322e498d034cdc8b1342016d6a9931653c08a72a64bc97f42690ab03ff483ba6f6ec53141a7d8df45738f73d29c26a3cd2ccb00ce7c32f164677d18809ec672cc6fef0117e68b8998205902906fca41d53b54d2e3f3993957fc6f9b1f2c88c6f35ec4d1c335a6dcb3c2bc71e5ab83afd83ad0550f2e60072d2d6899f3e5be82578e4eabdef1bdaee6b17d46e2f0c5415afbdca1bf5fc0fef42cea8a923c1dfa471f2fed4e9cdc07297f8014cb53b2901156286ee7f9fe690224c295f74eb6e63477633d75f2cdc66b5a384526cc4cec97a2334be3aa12229fbc95aed3948218668ee444f0951cb12c0908035322faaaa31c2d434f58ed21a6e2ad8611755f6fc5cf7a7aecbb9452b85d77b21cd933da5224215485f2036a5d52111a3a31a31a97f7920640ef2b05ce7270b64cc0a62f90367ee6ebfbfe8300e78ac4ae7f736982032fdf1de93acc6fe67599e7e0c92b1615235e67a7291398e71b07fc9e60e6f578e80bd76b7b834bd8f36a65fdc0b234e66b4aa7e2e40f8e8ec9c70b50ebba034aa5a4bb94d5093c91db5e04d44c7fad803";
    public static final Signature OCULUS_BUNDLES_SIGNATURE = new Signature(OCULUS_BUNDLES_CERTIFICATE);
    public static final String OCULUS_CORE_CERTIFICATE = "308205cf308203b7a0030201020204097e91c1300d06092a864886f70d01010b0500308197310b3009060355040613025553311330110603550408130a43616c69666f726e6961311330110603550407130a4d656e6c6f205061726b31233021060355040a131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c4331233021060355040b131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c43311430120603550403130b4f63756c75732041707073301e170d3139303730343131333935315a170d3436313131393131333935315a308197310b3009060355040613025553311330110603550408130a43616c69666f726e6961311330110603550407130a4d656e6c6f205061726b31233021060355040a131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c4331233021060355040b131a46616365626f6f6b20546563686e6f6c6f676965732c204c4c43311430120603550403130b4f63756c7573204170707330820222300d06092a864886f70d01010105000382020f003082020a0282020100b815642a5c201c8da044905dafbb06c05fddb75449cc75a716a0434da0493a52a4d60643532c237e76cb41d2c32a5e4474f12b37f632a809b5a5ceb8c6289ecf828e736b4ec452b1c2757bd0b5ed13eff5a7a47ab2ef6cdf60a8d749e9e8603f30d12b5db8f734c57578a2a4ce5ff5737c1f02c629d2dcb4f82726674663a699de94463ff370fa9eb48158831bf0b68bd6ec84293182d52e860bdd81bb9ce9cdd6a77fea0df25004da34d3cce7a31069646aa4628fba9ccb0952163793be9a4edf49a53f83179c2f44d8c87d6c129e8d590af0f3fced26201ee43b31fccd93b497cd198c5cd1f98d64c4f7ad79ca47278c02652d5dc072a99807e22f473f7bae4da2bafcae48cd49ad55a764b8d39f329f6175eac9074307deff48b1c4a6e19b748766617768c31a17feae8478eb7f9ebfb61af2b9f5a692b636342d7ebc53d7146626b51801b5a0a102e281475decdefb8683d0ca2843dc7aaeb6759bcab0dcba38db44f5d7f797a1c9c02dd159c0e3c72b14dc882d31c24ad8abee4ce0f7f0205e05efef551e2551a61e96636ed5dfd7435d3d24f5a130a12f10452cc954aa770d5d95e3e0cb9d7b05c5506a7947f8ff3437b3d82274fdaa156d85bfb7fbcb4fa5557080fedab86520b71d3d533e7bb3e7e3ebe9712b68e755d700b0afbf7595ffa80a6b3d90bfe599769138ee6515df415e32c0abed2b8e4aea1f47351f490203010001a321301f301d0603551d0e04160414a3c939e6b71c43bc9193f773c0351be1aff311f9300d06092a864886f70d01010b050003820201004e4c865231f2a3583c6af9488ecf9aa96b1ee84e2d7bfad00581537dc70c54ecfcf360e563d018efa4cdd77bf6fc4c9f1a20ebd4215cf1538686d4089bdcc8adb6ae4497ead14b0c18efd52ce93357019973e94e16b3326c60885b1bdc5f9cad1098f16732935876d8d19bda3ceeaaa69a83c4e2231710c6735f88abdfffe3dfa2be3f18f894923441b4e4a9c9664ed6294c16d7c073547063bfdbbefad184a001f1dce2feffdc96d10eb16a3bfbcd382cd297ca3b4edb7ebacd1a9931a1368a5157ceaebbca5b39bd5db5461a340db8ec076d38a94bcdd042bfb51388158ba97a49b631c1762fa746c7bb36eaf15cae23fa993cfb052e824f2ae173ea27b50a6cca91eb955f4faf131514757e5a3ecae9df6d23771279b7742f28fb9ce394eb9e03926f550716f1312355a5efdc479d8347b516cff55cb14307c81280142cacf2fd6562a2baacbfc97869272b1c51abf79121de8bab227e001313d4115eb9b33a332ebb34e290f14b5352ef1038f7a5f3740c9d1136b3526de4331deb4fa529c36a17c04c3e64d4f6ebda73d6e6e18281e3948fd11e685eab6611be1bc62839bce5962715e7f6537e6c083bcd3538d17407b26c850cd6b3f5e2149fdef027a6cfb7ed0f93c928aca14927b10c6a3f104ba1b10413af8b1868b84a004e5ddd065fff26a7bb3c818bb5a2d69aedf9441ef9033efae3ef52282a96f66d4b4ff3f2";
    public static final Signature OCULUS_CORE_SIGNATURE = new Signature(OCULUS_CORE_CERTIFICATE);
    public static final String OCULUS_OS_PLATFORM_CERTIFICATE = "308204a830820390a003020102020900b3998086d056cffa300d06092a864886f70d0101040500308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d301e170d3038303431353232343035305a170d3335303930313232343035305a308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d30820120300d06092a864886f70d01010105000382010d003082010802820101009c780592ac0d5d381cdeaa65ecc8a6006e36480c6d7207b12011be50863aabe2b55d009adf7146d6f2202280c7cd4d7bdb26243b8a806c26b34b137523a49268224904dc01493e7c0acf1a05c874f69b037b60309d9074d24280e16bad2a8734361951eaf72a482d09b204b1875e12ac98c1aa773d6800b9eafde56d58bed8e8da16f9a360099c37a834a6dfedb7b6b44a049e07a269fccf2c5496f2cf36d64df90a3b8d8f34a3baab4cf53371ab27719b3ba58754ad0c53fc14e1db45d51e234fbbe93c9ba4edf9ce54261350ec535607bf69a2ff4aa07db5f7ea200d09a6c1b49e21402f89ed1190893aab5a9180f152e82f85a45753cf5fc19071c5eec827020103a381fc3081f9301d0603551d0e041604144fe4a0b3dd9cba29f71d7287c4e7c38f2086c2993081c90603551d230481c13081be80144fe4a0b3dd9cba29f71d7287c4e7c38f2086c299a1819aa48197308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d820900b3998086d056cffa300c0603551d13040530030101ff300d06092a864886f70d01010405000382010100572551b8d93a1f73de0f6d469f86dad6701400293c88a0cd7cd778b73dafcc197fab76e6212e56c1c761cfc42fd733de52c50ae08814cefc0a3b5a1a4346054d829f1d82b42b2048bf88b5d14929ef85f60edd12d72d55657e22e3e85d04c831d613d19938bb8982247fa321256ba12d1d6a8f92ea1db1c373317ba0c037f0d1aff645aef224979fba6e7a14bc025c71b98138cef3ddfc059617cf24845cf7b40d6382f7275ed738495ab6e5931b9421765c491b72fb68e080dbdb58c2029d347c8b328ce43ef6a8b15533edfbe989bd6a48dd4b202eda94c6ab8dd5b8399203daae2ed446232e4fe9bd961394c6300e5138e3cfd285e6e4e483538cb8b1b357";
    public static final Signature OCULUS_OS_PLATFORM_SIGNATURE = new Signature(OCULUS_OS_PLATFORM_CERTIFICATE);
    public static final String OCULUS_PROD_CERTIFICATE = "3082038030820268a00302010202045390c873300d06092a864886f70d0101050500308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e301e170d3134303630353139343334375a170d3431313032313139343334375a308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e30820122300d06092a864886f70d01010105000382010f003082010a0282010100810913c89c710f0c1baf44cbd759cb193a9ceacc3ff64f6c710519cdc30f55f913285c74e8fd3e3220e55524cb881921a4de9b68a2f6a55eedd1da6c7f65f11adb983831e87797d70f411fd654dffbec956308bd7bbd8fb64215f15f315831a869b3ebc90a268b069e4b15e5861cbe1ef2b82c543f394410324891f161e0cecd1544c2b3e4be220e61312d1169950b3fc7c9c76c5eb4b723033caf65f5a9aadcb817c7923daea4266d0e874fd1f82480090f70b631d2b4ee4704c406c30c0d31cd75a9159ba37002ccfdff6752d61543252d5030ec0fff14e505b514027b702641b0aa6e65e5a3b80c847fc3f866d936234e7b91f8129749ac5383067bd74dcb0203010001300d06092a864886f70d01010505000382010100457e0ddb298616761934a9ff2a91048872af6f004855a0e866f4ffea0a4dfb1ff0aa2cf4ffac55f87d2d8e8273a867d4ef63d2fecb468d081ad405b3359fc576eeac839bacebb111d65f39a6930509b2986268c6a65554dfbd3eb40904b9a1d0b476c3a94b128bd975cd285635e2a225c4c3f7664eb8b98962d45416705805e4434bb826c7cb095c7ddb22cf99f92b6bc4e1b2a7e93d0ecb8d9b4543efed428589c2d52b16cde1c52dade04ffddcd3aa70aacef2a5c8dd89961f7e2b425ed1400f9673278bee714777d6b681eebd176de9345be39c800a4f78c41714d61018dbd5f3750fc8fe7ba35c2172b796fb5abb6c2b61528d1cddc84d2e16b685d02e66";
    public static final Signature OCULUS_PROD_SIGNATURE = new Signature(OCULUS_PROD_CERTIFICATE);
    private static final String OCULUS_TV_CERTIFICATE = "3082058930820371a00302010202042291b80c300d06092a864886f70d01010b05003075310b3009060355040613025553310b3009060355040813024341311330110603550407130a4d656e6c6f205061726b31173015060355040a130e4f63756c75732056522c204c4c4331173015060355040b130e4f63756c75732056522c204c434331123010060355040313094f63756c7573205456301e170d3137313131373134353531365a170d3435303430343134353531365a3075310b3009060355040613025553310b3009060355040813024341311330110603550407130a4d656e6c6f205061726b31173015060355040a130e4f63756c75732056522c204c4c4331173015060355040b130e4f63756c75732056522c204c434331123010060355040313094f63756c757320545630820222300d06092a864886f70d01010105000382020f003082020a02820201009d4bef7ce3f9d6eaec134228dd96b890095b9bfd038c7e4e63cb1f2db156f9b6042967a9343b4c72dfaaf45cbee82ea4c5824496e72d452d9f7c19d3c364bd192322cef671dbbf019f5fc00d954d170a6dd3a8508cbe53ec1893c396026fe34417dc7cfa1ac0496ccd364a86174c33770a4526d57db24101e1d2b6d965236fb117519ed22b55da5ddde4e6e087f823d4ebb13671d9066067f37ce144c65f5038c0aed7fb9cc615e5dc5cde56a711afb48634bcc87984ead764cf3ad1b67cb5415838d6266f827a1d94519077c71c495913aa30390542633be129b4019deb001be0fb41d25616553c405d2b1eae49c9036c83f1aec10489c115d05b0456190b6e9a31111a6d008060ed9da23307f94ccc426bb369160b0fd3ad883885a2d30c2c05fd312be46da97d50de4156370ccf4036f44fa97eee72a86553b19fc52eb0d51ad5b6e789d5d03b837dcee24a9f09a808d7413d673fae1b1701129b9a0eab004494595eba8bb40bbc2b52964cb600764b8ec89e3f7c16c829ff3d5a20c56e0001d865c12fb8c81174bdf82c7aa0b41888db22e39b9cb0a65b60192b363c5289f70eab125f253f1ca42ecef3bd6bd76de1f13a11901a28f3681fec9a85d82489f519a23d30eea4a9c8bd8856658cb5fa42857e5c60a72ce82ce4552e0b93171ac49d0309f87b53fa3ef206d9690c6a4778d48cf9102a61621944fb77ed576ca90203010001a321301f301d0603551d0e041604146017d5546abfcb2963e2a0851861e4a10a5b6667300d06092a864886f70d01010b05000382020100989989c9f0fd3a69958d55aebb94b732e5c43ab4563876dc164da078321d83fc5e0c422f7d76aba74b9bced4a624257709e6f910f0032588d3621fe05e486d796fca65c9e7c221a44f5dbf4572e54845df833daeb091591cca3965c919e26f11487b19112649e5211bede0f29ce752b0c6751b5ea6b1c1b151f19b935b0a50c94c84bc316c2a2ff76ee640053a90bf30841b063472b50560b581b02dc3ca7859de1cb7523b7a5a7a707ce8c3134a0d5f71fb135554940da2f7d2925e02770cba371f3d591f4f3134f69380ac5652da29d674c41d705f82e4bd3f64589dfd672c91c94e3cf613c3cf67b06ccb9a6c8949e45fac10db6d47c29ff407188f1c788ea2625262b8366ae5ed0f1f2e9f2343a48d4d3948ca2680c05b4935ebc98c99ec3996dc14f2c9f18a6d1adc358db071c03dfc5f95b77e04842a7c38b54aa640b261fe3e8de26221e181fd5acf2f1b33014b5f936cf60196d6fd07b570a1710f5cea409f7b82aa4b60f54c11e12d19699184e05c4be694b4e2555d4e3f1b7642550a19001bef318aef725ade11ad625765096856d12bc1726c1940cf2944c50c1d8096b11dff366414c02824106720ec82353026823682dde46c407b721c38cd46f7c43c9382dde3c7efda13637a2c9b0f54c20b0c5537b31ca48237017d450250897985b887f39f180c5d634d8a541f3cb544a534fb03299916220fa3d7a7d665";
    public static final Signature OCULUS_TV_SIGNATURE = new Signature(OCULUS_TV_CERTIFICATE);
}
