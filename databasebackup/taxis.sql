PGDMP                       |            verceldb    16.3    16.3                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            
           1262    16389    verceldb    DATABASE     j   CREATE DATABASE verceldb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE verceldb;
                default    false                       0    0    DATABASE verceldb    ACL     2   GRANT ALL ON DATABASE verceldb TO neon_superuser;
                   default    false    3338            �            1259    32771    taxis    TABLE     a   CREATE TABLE public.taxis (
    id integer NOT NULL,
    plate character varying(15) NOT NULL
);
    DROP TABLE public.taxis;
       public         heap    default    false            �            1259    32770    taxis_id_seq    SEQUENCE     �   CREATE SEQUENCE public.taxis_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.taxis_id_seq;
       public          default    false    216                       0    0    taxis_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.taxis_id_seq OWNED BY public.taxis.id;
          public          default    false    215            o           2604    32774    taxis id    DEFAULT     d   ALTER TABLE ONLY public.taxis ALTER COLUMN id SET DEFAULT nextval('public.taxis_id_seq'::regclass);
 7   ALTER TABLE public.taxis ALTER COLUMN id DROP DEFAULT;
       public          default    false    215    216    216                      0    32771    taxis 
   TABLE DATA           *   COPY public.taxis (id, plate) FROM stdin;
    public          default    false    216   �                  0    0    taxis_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.taxis_id_seq', 1, false);
          public          default    false    215            q           2606    32776    taxis taxis_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.taxis
    ADD CONSTRAINT taxis_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.taxis DROP CONSTRAINT taxis_pkey;
       public            default    false    216            s           2606    32778    taxis taxis_plate_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.taxis
    ADD CONSTRAINT taxis_plate_key UNIQUE (plate);
 ?   ALTER TABLE ONLY public.taxis DROP CONSTRAINT taxis_plate_key;
       public            default    false    216                  7249	CNCJ-2997
    10133	PAOF-6727
    2210	FGMG-3071
    1065	GHDN-9291
    7956	CCKF-1601
    252	LAHG-7611
    9557	BOIG-0354
    4485	LLEL-9271
    6418	GHGH-1458
    8825	HBEE-6337
    974	FNDF-2678
    8935	GAJG-2446
    6772	NOCB-3788
    6680	MCCI-5437
    4831	FHCI-2846
    3300	NBAC-4573
    3859	GPGM-7365
    7938	HLBO-2375
    1999	MOCP-7556
    7215	JJNC-1645
    6782	KOFG-2986
    4055	KPKO-3451
    3560	GJCC-2787
    5561	FKOH-3490
    7813	FFGI-2329
    8716	EONJ-3377
    10293	POOD-6790
    4028	HNHM-3476
    3282	MDDB-5497
    8311	GNGM-2252
    9408	CHGM-2476
    3510	FEHJ-7325
    7300	HHJP-8573
    7088	HDBL-4695
    296	CGEF-7101
    6598	FHLB-7962
    4910	GDEE-7052
    6642	DKOK-3814
    3335	EHMB-5718
    9275	ENPB-7532
    9098	MIHI-5177
    4105	MLGC-5558
    2816	AFOI-1144
    7027	OGHL-0787
    1364	EGFM-5153
    655	NOGG-5935
    9440	FDCH-3572
    6163	PEPP-9872
    5170	FCBN-3511
    167	IIJB-4867
    9916	BLDG-3162
    8604	HOMH-4581
    8300	EDOF-2862
    8305	GIBC-2378
    71	CLLD-1805
    7218	MNBE-4846
    2761	CFBG-2755
    624	FKLI-9441
    626	ECFG-9397
    4728	DABK-9431
    4145	LGHO-2055
    9452	KEBK-7570
    6374	KCGK-4346
    8484	FHEE-8646
    8491	BNJE-8175
    8751	GMEC-3019
    4056	CIKI-8122
    280	IHIH-1812
    6286	MLHK-7222
    2886	AIGG-5536
    2925	BADO-1416
    3492	OKCA-5223
    7131	OKAL-8398
    1139	MFEF-8699
    9271	JDOL-5035
    4416	KHIE-6756
    2270	GJGE-8317
    2522	LGBJ-0156
    9406	EIGN-5732
    2542	DOHO-6151
    2230	LOFJ-3431
    4787	NGKN-4357
    7493	MOJF-6958
    1963	NBNI-1458
    15	FNHK-3772
    9328	EHBL-7645
    6118	IBNE-0418
    5210	IPGK-3957
    9061	NPGE-7240
    56	JIMF-2287
    9678	FLPG-1136
    7169	CKEH-6725
    2183	MKMJ-7264
    6790	MGMA-6670
    6129	FLGH-8619
    8466	GGHB-2669
    2211	FHID-7265
    4449	JGIE-0333
    6700	BLMO-4997
    2112	GHNJ-1555
    7934	PCKL-3328
    5224	KMDK-1502
    7150	DNCJ-7132
    3979	IEMK-7327
    10206	INHN-2688
    923	IBIJ-5577
    8362	GPGD-1746
    3082	BDFM-5551
    8251	GOAJ-6841
    8320	OOMH-5539
    10207	JLDK-4535
    2179	MKFF-1845
    7534	MICH-4553
    8433	IGMB-9195
    8585	MDEF-7585
    376	DDNG-0487
    10247	OBEF-5861
    515	EHCE-5183
    7613	FCKB-6558
    2783	LIEL-8257
    4453	DNCM-7790
    6298	DCFM-2125
    9654	ENKO-4271
    2717	LBOK-2444
    8011	FJEC-3215
    9836	FHGJ-5518
    5256	HMMN-0507
    5610	KOGN-4837
    165	HKNN-8042
    5803	HNMB-4988
    9451	MLMM-3869
    5838	MBND-8204
    21	NNEL-8793
    6247	KMGB-7305
    570	AMGF-6934
    5270	BDLP-4643
    3942	JLBH-4071
    8776	MDDJ-5215
    1049	BHNL-4225
    9513	FNHL-5802
    6058	JBKB-1428
    7779	KGHD-5576
    6056	BJFI-7501
    8869	HINB-4821
    609	PNCB-3390
    4242	LGEF-9081
    9300	KEJH-7652
    7397	DHFJ-3563
    8733	GMGM-1378
    3029	MBDM-1187
    9643	GHDD-1743
    9298	EMOJ-3697
    1806	HGIJ-7345
    7834	GKGI-7231
    2153	KHGM-2815
    6899	KACP-6171
    7217	IELF-5280
    2692	NEIJ-5872
    6768	FCPJ-0116
    8230	CDKL-3488
    6643	KJDE-7135
    8410	GNDO-6910
    8150	BBJC-1672
    5540	OHAE-8815
    6031	HFND-4178
    3812	AAML-9871
    2677	PGDC-1949
    3458	GGDE-3387
    9238	LLOD-8718
    9018	MOJL-7377
    8377	LPOM-8373
    7219	LPJA-3471
    7189	DKFB-4482
    2351	FIPC-8785
    108	LNGK-1108
    8974	CKNH-1553
    7646	FOFM-3206
    3379	MPNJ-7725
    1475	KJDM-6267
    1017	MIFO-1824
    9217	MJDD-8286
    6737	DLEP-5452
    2719	BKAM-8657
    3663	DOKF-7876
    7716	IAMG-2604
    3572	GFHP-7928
    7302	KDJI-8221
    5240	COIO-8354
    10026	FFNJ-9813
    2353	DIFO-4571
    6841	MGKK-5876
    8715	NFJM-6129
    5370	HBJH-8671
    804	CDBJ-7875
    4397	EGDN-3052
    7262	FDAP-3298
    3900	EIED-2133
    2611	EGOA-2654
    9357	BLFH-6860
    7957	BAJW-7971
    \.


     